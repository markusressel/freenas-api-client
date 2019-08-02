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

package de.markusressel.freenasrestapiclient.api.v2.auth

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class AuthApiImpl(val websocketApiClient: WebsocketApiClient) : AuthApi {
    override suspend fun checkUser(username: String, password: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("auth.check_user", username, password)
    }

    override suspend fun generateToken(ttl: Int?): Result<JsonElement, Exception> {
        val attrs = jsonObject()
        return websocketApiClient.callMethod("auth.generate_token", ttl, attrs)
    }

    override suspend fun login(username: String, password: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("auth.login", username, password)
    }

    override suspend fun logout(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("auth.logout")
    }

    override suspend fun authenticate(token: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("auth.token", token)
    }
}