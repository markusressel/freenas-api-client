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
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class BootEnvApiImpl(val websocketApiClient: WebsocketApiClient) : BootEnvApi {
    override suspend fun getBootEnvs(): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun activateBootEnv(id: String): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun createBootEnv(name: String, source: String?): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setBootEnvAttribute(keep: Boolean): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateBootEnv(id: String, name: String): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteBootEnv(id: String): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}