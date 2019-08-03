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

package de.markusressel.freenasrestapiclient.api.v2.ipmi

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class IpmiApiImpl(val websocketApiClient: WebsocketApiClient) : IpmiApi {
    override suspend fun getIpmiChannels(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("ipmi.channels")
    }

    override suspend fun identifyIpmi(seconds: Int, force: Boolean): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("ipmi.identify", mapOf(
                "seconds" to seconds,
                "force" to force
        ))
    }

    override suspend fun isIpmiLoaded(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("ipmi.is_loaded")
    }

    override suspend fun getIpmi(queryFilters: List<QueryFilter>,
                                 queryOptions: QueryOptions): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("ipmi.query", queryFilters, queryOptions)
    }

    override suspend fun updateIpmi(channel: Int,
                                    ipaddress: String,
                                    netmask: String,
                                    gateway: String,
                                    password: String,
                                    dhcp: Boolean,
                                    vlan: Int): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("ipmi.update", channel, mapOf(
                "ipaddress" to ipaddress,
                "netmask" to netmask,
                "gateway" to gateway,
                "password" to password,
                "dhcp" to dhcp,
                "vlan" to vlan
        ))
    }
}