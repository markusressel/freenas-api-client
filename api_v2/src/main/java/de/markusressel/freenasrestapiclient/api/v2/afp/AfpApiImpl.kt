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

package de.markusressel.freenasrestapiclient.api.v2.afp

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.addProperty
import com.github.salomonbrys.kotson.jsonObject
import com.github.salomonbrys.kotson.toJsonArray
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class AfpApiImpl(val websocketApiClient: WebsocketApiClient) : AfpApi {
    override suspend fun getAfpConfig(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("afp.config")
    }

    override suspend fun updateAfpConfig(chmodRequest: AfpApi.ChmodRequest?,
                                         mapAcls: AfpApi.MapAcl?,
                                         globalAux: String?,
                                         dbpath: String?,
                                         connectionsLimit: Int?,
                                         bindip: List<String>?,
                                         guest: Boolean?,
                                         guestUser: String?): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addProperty("guest", guest)
            addProperty("guest_user", guestUser)
            addProperty("bindip", bindip?.toJsonArray())
            addProperty("connections_limit", connectionsLimit)
            addProperty("dbpath", dbpath)
            addProperty("global_aux", globalAux)
            addProperty("map_acls", mapAcls?.toJsonValue())
            addProperty("chmod_request", chmodRequest?.toJsonValue())
        }
        return websocketApiClient.callMethod("afp.update", arguments)
    }
}