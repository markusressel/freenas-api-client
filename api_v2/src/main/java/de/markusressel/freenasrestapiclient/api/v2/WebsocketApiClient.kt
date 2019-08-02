/*
 * Copyright (c) 2019 Markus Ressel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusressel.freenasrestapiclient.api.v2

import android.util.Log
import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.*
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import de.markusressel.commons.android.core.runOnUiThread
import de.markusressel.freenasrestapiclient.core.BasicAuthConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.net.ConnectException
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


typealias ApiListener = (Result<JsonElement, Exception>) -> Unit

class WebsocketApiClient(
        val url: String,
        val auth: BasicAuthConfig) {

    private var listener: WebsocketConnectionListener? = null

    private val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(3, TimeUnit.SECONDS)
            .pingInterval(30, TimeUnit.SECONDS)
            .authenticator { _, response ->
                val credentials = Credentials.basic(auth.username, auth.password)
                response.request().newBuilder()
                        .header("Authorization", credentials)
                        .build()
            }
            .build()

    private val responseListeners: MutableMap<String, ApiListener> = mutableMapOf()
    private val subscriptionListeners: MutableMap<String, ApiListener> = mutableMapOf()

    /**
     * Indicates if the websocket is connected
     */
    var isConnected = false

    private var webSocket: WebSocket? = null

    private var sessionId: String? = null

    private val jsonParser = JsonParser()

    /**
     * Set a listener to react to websocket events
     *
     * @param listener the listener to use or null to disable it
     */
    fun setListener(listener: WebsocketConnectionListener?) {
        this.listener = listener
    }

    /**
     * Connect the websocket.
     * If the socket is already connected this is a no-op.
     */
    suspend fun connect(): Result<Boolean, Exception> {
        return suspendCoroutine { continuation ->
            if (isConnected) {
                Log.w(TAG, "Already connected")
                continuation.resume(Result.of(true))
            }
            Log.d(TAG, "Connecting websocket...")

            val request = Request.Builder().url(url).build()

            webSocket = client.newWebSocket(request, object : WebSocketListener() {

                override fun onOpen(webSocket: WebSocket, response: Response?) {
                    Log.d(TAG, "Connection established")
                    createSession()
                }

                override fun onMessage(webSocket: WebSocket, text: String?) {
                    handleIncomingMessage(continuation, text ?: "")
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    Log.d(TAG, "Connection closed: $code $reason")
                    isConnected = false
                    this@WebsocketApiClient.listener?.onConnectionChanged(isConnected)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable?, response: Response?) {
                    Log.e(TAG, "Connection error: $response", t)
                    isConnected = false

                    try {
                        continuation.resume(Result.error(ConnectException("Error connecting: $t, $response")))
                    } catch (e: IllegalStateException) {
                    }

                    runOnUiThread {
                        this@WebsocketApiClient.listener?.onConnectionChanged(isConnected, response?.code(), t)
                    }
                }
            })
        }
    }

    /**
     * Internal method to handle incoming websocket messages
     */
    private fun handleIncomingMessage(resultListener: Continuation<Result<Boolean, Exception>>, message: String) {
        val json = jsonParser.parse(message).asJsonObject

        if (json.has(PROPERTY_ID)) {
            val responseId = json[PROPERTY_ID].string
            notifyListener(responseId, json)
            return
        }

        // the initial createSession response has no id so we can handle it here
        when (json[PROPERTY_MSG].string) {
            "connected" -> {
                sessionId = json["session"].string
                Log.d(TAG, "Session created: $sessionId")
                login(resultListener)
            }
            "failed" -> {
                isConnected = false
                this@WebsocketApiClient.listener?.onConnectionChanged(isConnected, -1, UnknownError(json["msg"].string))
            }
        }
    }

    /**
     * Notifies listeners
     *
     * @param responseId response id used to identify requester
     * @param json the response
     */
    private fun notifyListener(responseId: String, json: JsonObject) {
        val result = when {
            json.has(PROPERTY_ERROR) -> Result.error(Exception("${json[PROPERTY_ERROR]}"))
            else -> Result.of(json)
        }

        responseListeners[responseId]?.apply {
            invoke(result)
            responseListeners.remove(responseId)
        }
        subscriptionListeners[responseId]?.invoke(result)
    }

    /**
     * Connect and authenticate the session
     */
    private fun createSession() {
        Log.d(TAG, "Creating session...")
        webSocket?.send(jsonObject(
                PROPERTY_MSG to "connect",
                "version" to "1",
                "support" to jsonArray(1)
        ).toString())
    }

    /**
     * Send a login message
     */
    private fun login(resultListener: Continuation<Result<Boolean, Exception>>) {
        GlobalScope.launch {
            val result = callMethod("auth.login",
                    auth.username, auth.password,
                    suppressLog = true)

            result.fold(success = {
                Log.d(TAG, "Login response: $it")

                val responseObject = it.asJsonObject
                if (responseObject["result"].bool) {
                    if (!isConnected) {
                        this@WebsocketApiClient.listener?.onConnectionChanged(true)
                        isConnected = true
                        resultListener.resume(Result.of(isConnected))
                    }
                } else {
                    disconnect(1000, responseObject["msg"].string)
                    resultListener.resume(Result.error(ConnectException("Error logging in: ${responseObject["msg"]}")))
                }
            }, failure = { error ->
                disconnect(4000, "${error.message}")
                throw error
            })
        }

    }

    /**
     * Subscribe to a topic and get continuous updates
     *
     * @return a message identifier that can be used to unsubscribe
     */
    suspend fun subscribe(method: String, arguments: JsonElement? = null, listener: ApiListener): String {
        val messageId = generateMessageId()
        Log.d(TAG, "Subscribing '$messageId' to '$method' with parameters: '$arguments'")
        subscriptionListeners[messageId] = listener
        // TODO: subscribe to something
        return messageId
    }

    /**
     * Unsubscribe from a topic
     *
     * @param messageId the message id you got when subscribing
     */
    suspend fun unsubscribe(messageId: String) {
        // TODO: tell the server that we want to unsubscribe
        Log.d(TAG, "Unsubscribing '$messageId'")
        subscriptionListeners.remove(messageId)
    }

    /**
     * Call an api method
     *
     * @param method the method to call
     * @param argument method arguments
     * @param suppressLog whether to suppress message logging (useful for privacy sensitive requests/responses)
     */
    suspend fun callMethod(method: String, vararg argument: Any? = emptyArray(), suppressLog: Boolean = false): Result<JsonElement, Exception> {
        return suspendCoroutine { continuation ->
            try {
                val messageId = generateMessageId()
                if (!suppressLog) Log.d(TAG, "Calling method '$method' with parameters: '$argument' for '$messageId'")

                responseListeners[messageId] = { result ->
                    continuation.resume(result)
                }

                val jsonElementArgs = argument.map {
                    if (it is ApiEnum) {
                        it.toJsonValue()
                    } else {
                        it
                    }
                }.map {
                    if (it !is JsonElement) {
                        GSON.toJsonTree(it)
                    } else {
                        it
                    }
                }
                val arguments = jsonArray().apply {
                    addAll(jsonElementArgs)
                }

                sendMethodMessage(messageId = messageId, message = "method", method = method, arguments = arguments)
            } catch (e: Exception) {
                continuation.resumeWithException(e)
            }
        }
    }

    /**
     * Send a method message
     *
     * @param messageId unique identifier
     * @param message the message
     * @param method the method
     * @param arguments method parameters
     */
    private fun sendMethodMessage(messageId: String = generateMessageId(), message: String, method: String, arguments: JsonElement?) {
        val request = jsonObject().apply {
            addProperty(PROPERTY_ID, messageId)
            addProperty(PROPERTY_MSG, message)
            addProperty("method", method)
            addPropertyIfNotNull("params", arguments)
        }
        send(request)
    }

    private fun send(json: JsonElement) = send(json.toString())

    /**
     * Send a message over the websocket.
     *
     * @throws IllegalStateException when the websocket is disconnected
     */
    private fun send(message: String) {
        if (sessionId == null) {
            Log.w(TAG, "No active session!")
            return
        }

        webSocket?.send(message)
    }

    /**
     * Generates a new, random message id
     *
     * @return the generated id
     */
    private fun generateMessageId(): String {
        return UUID.randomUUID().toString()
    }

    /**
     * Disconnect a websocket
     *
     * @param code reason code in [1000..5000[ range
     * @param reason reason message
     */
    fun disconnect(code: Int, reason: String) {
        webSocket?.close(code, reason)
        webSocket = null
        responseListeners.clear()
        subscriptionListeners.clear()
        if (isConnected) {
            isConnected = false
            this@WebsocketApiClient.listener?.onConnectionChanged(isConnected, code)
        }
    }

    /**
     * Shutdown the websocket
     */
    fun shutdown() {
        disconnect(1000, "Client shutdown")
        client.dispatcher().executorService().shutdown()
    }

    companion object {
        private const val TAG = "WebsocketApiClient"
        private const val PROPERTY_ID = "id"
        private const val PROPERTY_MSG = "msg"
        private const val PROPERTY_ERROR = "error"

        private val GSON = Gson()
    }

}
