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

package de.markusressel.freenasrestapiclient.api.v2.ftp

import de.markusressel.freenasrestapiclient.api.v2.ApiEnum
import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface FtpApi {

    enum class TlsPolicy(private val jsonValue: String) : ApiEnum {
        ON("on"),
        OFF("off"),
        DATA("data"),
        NO_DATA("!data"),
        AUTH("auth"),
        CONTROL("ctrl"),
        CONTROL_AND_DATA("ctrl+data"),
        CONTROL_NO_DATA("ctrl+!data"),
        AUTH_AND_DATA("auth+data"),
        AUTH_NO_DATA("auth+!data");

        override fun toJsonValue(): String = jsonValue
    }

    /**
     * Get FTP config
     *
     * @param listener result listener
     */
    fun getFtpConfig(listener: ApiListener)

    /**
     * Set the FTP config
     *
     * TODO: maaaany params
     *
     * @param listener result listener
     */
    fun setFtpConfig(port: Int?,
                     clients: Int?,
                     ipConnections: Int?,
                     loginAttempts: Int?,
                     timeout: Int?,
                     rootLogin: Boolean?,
                     onlyAnonymous: Boolean?,
                     anonpath: String?,
                     onlylocal: Boolean?,
                     banner: String?,
                     filemask: String?,
                     dirmask: String?,
                     fxp: Boolean?,
                     resume: Boolean?,
                     defaultroot: Boolean?,
                     ident: Boolean?,
                     reversedns: Boolean?,
                     masqaddress: String?,
                     passiveportsmin: Int?,
                     passiveportsmax: Int?,
                     localuserbw: Int?,
                     localuserdlbw: Int?,
                     anonuserbw: Int?,
                     anonuserdlbw: Int?,
                     tls: Boolean?,
                     tls_policy: TlsPolicy?,
                     tls_opt_allow_client_renegotiations: Boolean?,
                     tls_opt_allow_dot_login: Boolean?,
                     tls_opt_allow_per_user: Boolean?,
                     tls_opt_common_name_required: Boolean?,
                     tls_opt_enable_diags: Boolean?,
                     tls_opt_export_cert_data: Boolean?,
                     tls_opt_no_cert_request: Boolean?,
                     tls_opt_no_empty_fragments: Boolean?,
                     tls_opt_no_session_reuse_required: Boolean?,
                     tls_opt_stdenvvars: Boolean?,
                     tls_opt_dns_name_required: Boolean?,
                     tls_opt_ip_address_required: Boolean?,
                     ssltls_certificate: Int?,
                     options: String?,
                     listener: ApiListener)

}