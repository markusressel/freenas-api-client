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

package de.markusressel.freenasrestapiclient.api.v2.bootenv

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.addPropertyIfNotNull
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class BootEnvApiImpl(val websocketApiClient: WebsocketApiClient) : BootEnvApi {
    override suspend fun getBootEnvs(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("bootenv.query")
    }

    override suspend fun activateBootEnv(id: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("bootenv.activate")
    }

    override suspend fun createBootEnv(name: String, source: String?): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addProperty("name", name)
            addPropertyIfNotNull("source", source)
        }
        return websocketApiClient.callMethod("bootenv.activate", arguments)
    }

    override suspend fun setBootEnvAttribute(id: String, keep: Boolean): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addProperty("id", id)
            addProperty("keep", keep)
        }
        return websocketApiClient.callMethod("bootenv.set_attribute", arguments)
    }

    override suspend fun updateBootEnv(id: String, name: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addProperty("id", id)
            addProperty("name", name)
        }
        return websocketApiClient.callMethod("bootenv.update", arguments)
    }

    override suspend fun deleteBootEnv(id: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addProperty("id", id)
        }
        return websocketApiClient.callMethod("bootenv.delete", arguments)
    }
}