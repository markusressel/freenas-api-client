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

import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface InterfacesApi {

    /**
     * Get all IPv4 / Ipv6 from all valid interfaces, excluding lo0, bridge and tap.
     * Choices is a dictionary with defaults to
     * {'ipv4': True, 'ipv6': True}
     * Returns a list of dicts - eg -
     * [
     *   {
     *     "type": "INET6",
     *     "address": "fe80::5054:ff:fe16:4aac",
     *     "netmask": 64
     *   },
     *   {
     *     "type": "INET",
     *     "address": "192.168.122.148",
     *     "netmask": 24,
     *     "broadcast": "192.168.122.255"
     *   },
     * ]
     *
     * @param listener result listener
     */
    fun getInterfaceIpsInUse(listener: ApiListener)

    /**
     * Queries a list of interfaces
     *
     * TODO: query params
     *
     * @param listener result listener
     */
    fun getInterfaces(listener: ApiListener)

//    TODO: interfaces.websocket_interface
//    TODO: interfaces.websocket_local_ip

}