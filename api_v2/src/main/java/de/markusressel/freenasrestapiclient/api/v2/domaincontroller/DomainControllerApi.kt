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

package de.markusressel.freenasrestapiclient.api.v2.domaincontroller

import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface DomainControllerApi {

    enum class DomainControllerRole {
        DC
    }

    enum class DnsBackend {
        SAMBA_INTERNAL,
        BIND9_FLATFILE,
        BIND9_DLZ,
        NONE
    }

    enum class ForestLevel {
        `2000`,
        `2003`,
        `2008`,
        `2008_R2`,
        `2012`,
        `2012_R2`
    }

    /**
     * Get the domain controller config
     *
     * @param listener result listener
     */
    fun getDomainControllerConfig(listener: ApiListener)

    /**
     * Set the domain controller config
     *
     * TODO: params
     *
     * @param listener result listener
     */
    fun setDomainControllerConfig(realm: String?, domain: String?, role: DomainControllerRole?,
                                  dnsBackend: DnsBackend?, dnsForwarder: String?,
                                  forestLevel: ForestLevel, password: String?,
                                  kerberosRealm: Int?, listener: ApiListener)

}