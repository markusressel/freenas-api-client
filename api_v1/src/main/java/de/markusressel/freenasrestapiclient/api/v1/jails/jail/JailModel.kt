/*
 * Copyright (c) 2018 Markus Ressel
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

package de.markusressel.freenasrestapiclient.api.v1.jails.jail

/**
 * Created by Markus on 06.02.2018.
 */
class JailModel(val id: Long, val jail_alias_bridge_ipv4: String?,
                val jail_alias_bridge_ipv6: String?, val jail_alias_ipv4: String?,
                val jail_alias_ipv6: String?, val jail_autostart: Boolean,
                val jail_bridge_ipv4: String?, val jail_bridge_ipv4_netmask: String?,
                val jail_bridge_ipv6: String?, val jail_bridge_ipv6_prefix: String?,
                val jail_defaultrouter_ipv4: String?, val jail_defaultrouter_ipv6: String?,
                val jail_flags: String?, val jail_host: String, val jail_iface: String,
                val jail_ipv4: String?, val jail_ipv4_netmask: String?, val jail_ipv6: String?,
                val jail_ipv6_prefix: String?, val jail_mac: String?, val jail_nat: Boolean,
                val jail_status: String?, val jail_type: String?, val jail_vnet: Boolean) {

    class SingleDeserializer : ResponseDeserializable<JailModel> {
        override fun deserialize(content: String): JailModel? {
            return Gson()
                    .fromJson(content)
        }
    }

    class ListDeserializer : ResponseDeserializable<List<JailModel>> {

        override fun deserialize(content: String): List<JailModel>? {
            if (content.isEmpty()) {
                return emptyList()
            }

            return Gson()
                    .fromJson(content)
        }

    }

}