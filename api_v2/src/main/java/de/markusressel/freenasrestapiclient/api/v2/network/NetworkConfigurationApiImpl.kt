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

package de.markusressel.freenasrestapiclient.api.v2.network

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class NetworkConfigurationApiImpl(val websocketApiClient: WebsocketApiClient) : NetworkConfigurationApi {

    override suspend fun getNetworkConfig(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("network.configuration.config")
    }

    override suspend fun updateNetworkConfig(hostname: String?,
                                             hostname_b: String?,
                                             hostname_virtual: String?,
                                             domain: String?,
                                             domains: List<String>?,
                                             ipv4gateway: String?,
                                             ipv6gateway: String?,
                                             nameserver1: String?,
                                             nameserver2: String?,
                                             nameserver3: String?,
                                             httpproxy: String?,
                                             netwait_enabled: Boolean?,
                                             netwait_ip: List<String>?,
                                             hosts: List<String>?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("network.configuration.update", mapOf(
                "hostname" to hostname,
                "hostname_b" to hostname_b,
                "hostname_virtual" to hostname_virtual,
                "domain" to domain,
                "domains" to domains,
                "ipv4gateway" to ipv4gateway,
                "ipv6gateway" to ipv6gateway,
                "nameserver1" to nameserver1,
                "nameserver2" to nameserver2,
                "nameserver3" to nameserver3,
                "httpproxy" to httpproxy,
                "netwait_enabled" to netwait_enabled,
                "netwait_ip" to netwait_ip,
                "hosts" to hosts
        ))
    }

    override suspend fun validateGeneralNetworkSettings(data: String, schema: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("network.configuration.validate_general_settings", data, schema)
    }

}