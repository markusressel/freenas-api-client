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

package de.markusressel.freenasrestapiclient.api.v2.alert

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class AlertApiImpl(val websocketApiClient: WebsocketApiClient) : AlertApi {
    override suspend fun dismissAlert(id: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addProperty("id", id)
        }
        return websocketApiClient.callMethod("alert.dismiss", arguments)
    }

    override suspend fun flushAlerts(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("alert.flush_alerts")
    }

    override suspend fun listAlerts(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("alert.list")
    }

    override suspend fun listAlertPolicies(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("alert.list_policies")
    }

    override suspend fun listAlertSources(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("alert.list_sources")
    }

    override suspend fun processAlerts(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("alert.process_alerts")
    }

    override suspend fun restoreAlert(id: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addProperty("id", id)
        }
        return websocketApiClient.callMethod("alert.restore", arguments)
    }
}