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
import com.github.salomonbrys.kotson.*
import de.markusressel.commons.android.core.runOnUiThread
import de.markusressel.freenasrestapiclient.core.BasicAuthConfig
import okhttp3.*
import java.util.concurrent.TimeUnit

class WebsocketApiClient(
        val url: String,
        val auth: BasicAuthConfig) {

    private var listener: WebsocketConnectionListener? = null

    private val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .connectTimeout(3, TimeUnit.SECONDS)
            //            .pingInterval(30, TimeUnit.SECONDS)
            .authenticator { route, response ->
                val credential = Credentials.basic(auth.username, auth.password)

                response
                        .request()
                        .newBuilder()
                        .header("Authorization", credential)
                        .build()
            }
            .build()

    /**
     * Indicates if the websocket is connected
     */
    var isConnected = false

    private var webSocket: WebSocket? = null

    private var sessionId: String? = null

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
    fun connect() {
        if (isConnected) {
            Log.w(TAG, "Already connected")
            return
        }

        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response?) {
                isConnected = true
                listener?.onConnectionChanged(isConnected)
            }

            override fun onMessage(webSocket: WebSocket, text: String?) {
                if (text == null) {
                    return
                }

                handleIncomingMessage(text)

                listener?.onMessage(text)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                isConnected = false
                listener?.onConnectionChanged(isConnected)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable?, response: Response?) {
                isConnected = false

                Log.e(TAG, "Websocket error", t)
                runOnUiThread {
                    listener?.onConnectionChanged(isConnected, response?.code(), t)
                }
            }
        })

        createSession()
    }

    private fun handleIncomingMessage(message: String) {
        val json = message.toJson()
        when (json["msg"].string) {
            "connected" -> {
                sessionId = json["session"].string
                callMethod("auth.createSession", listOf(
                        "username" to auth.username,
                        "password" to auth.password
                ))
            }
            "failed" -> {
                throw IllegalStateException("Error connecting")
            }
        }
    }

    /**
     * Connect and authenticate the session
     */
    private fun createSession() {
        callMethod("connect", listOf(
                "version" to "1",
                "support" to jsonArray(1)
        ))
    }

    /**
     * Call an api method
     *
     * @param method the method to call
     * @param arguments method arguments
     */
    fun callMethod(method: String, arguments: List<Pair<String, Any?>>) {
        sendMessage(message = "method", method = method, arguments = arguments)
    }

    /**
     * Send a message over the websocket.
     *
     * @throws IllegalStateException when the websocket is disconnected
     */
    private fun send(message: String) {
        if (!isConnected) {
            throw IllegalStateException("There is no active connection!")
        }
        webSocket?.send(message)
    }

    /**
     * Send a message
     *
     * @param message the message
     * @param method the method
     * @param arguments method parameters
     */
    private fun sendMessage(message: String, method: String, arguments: List<Pair<String, Any?>>) {
        val currentSession = sessionId
        if (currentSession == null) {
            Log.w(TAG, "No active session!")
            return
        }
        val request = createRequest(sessionId = currentSession, message = message, method = method, arguments = arguments)
        send(request.toJsonObject().toString())
    }

    /**
     * Create a request
     */
    private fun createRequest(sessionId: String, message: String, method: String, arguments: List<Pair<String, Any?>>): List<Pair<String, Any?>> {
        return listOf(
                "id" to sessionId,
                "msg" to message,
                "method" to method,
                "params" to jsonArray(arguments)
        )
    }

    /**
     * Disconnect a websocket
     */
    fun disconnect(code: Int, reason: String) {
        webSocket?.close(code, reason)
        webSocket = null
        isConnected = false
    }

    /**
     * Shutdown the websocket
     */
    fun shutdown() {
        client.dispatcher().executorService().shutdown()
    }

    companion object {
        private const val TAG = "WebsocketApiClient"
    }

}
