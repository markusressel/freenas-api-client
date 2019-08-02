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

package de.markusressel.freenasrestapiclient.api.v2.interfaces

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class InterfacesApiImpl(val websocketApiClient: WebsocketApiClient) : InterfacesApi {
    override suspend fun getInterfaceIpsInUse(ipv4: Boolean,
                                              ipv6: Boolean): Result<JsonElement, Exception> {
        val ips = jsonObject(
                "ipv4" to ipv4,
                "ipv6" to ipv6
        )
        return websocketApiClient.callMethod("interfaces.ip_in_use", ips)
    }

    override suspend fun getInterfaces(queryFilters: List<QueryFilter>,
                                       queryOptions: QueryOptions): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("interfaces.query", queryFilters, queryOptions)
    }
}