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

package de.markusressel.freenasrestapiclient.api.v2.boot

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class BootApiImpl(val websocketApiClient: WebsocketApiClient) : BootApi {
    override suspend fun attachBootPool(dev: String, expand: Boolean?): Result<JsonElement, Exception> {
        val options = jsonObject().apply {
            addProperty("expand", expand)
        }
        return websocketApiClient.callMethod("boot.attach", dev, options)
    }

    override suspend fun detachBootPool(dev: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("boot.detach", dev)
    }

    override suspend fun getBootDisks(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("boot.get_disks")
    }

    override suspend fun getBootState(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("boot.get_state")
    }

    override suspend fun replaceBootLabel(label: String, dev: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("boot.replace", label, dev)
    }

    override suspend fun scrubBoot(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("boot.scrub")
    }

}