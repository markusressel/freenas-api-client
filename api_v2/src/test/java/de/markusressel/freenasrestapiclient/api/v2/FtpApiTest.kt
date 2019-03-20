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

package de.markusressel.freenasrestapiclient.api.v2

import de.markusressel.freenasrestapiclient.api.v2.base.TestBase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FtpApiTest : TestBase() {

    @Test
    fun testGetFtpConfig() {
        runBlocking {
            val result = underTest.getFtpConfig()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testSetFtpConfig() {
        runBlocking {
            val result = underTest.setFtpConfig(
                    port = null,
                    clients = null,
                    ipConnections = null,
                    loginAttempts = null,
                    timeout = null,
                    rootLogin = null,
                    onlyAnonymous = null,
                    anonpath = null,
                    onlylocal = null,
                    banner = null,
                    filemask = null,
                    dirmask = null,
                    fxp = null,
                    resume = null,
                    defaultroot = null,
                    ident = null,
                    reversedns = null,
                    masqaddress = null,
                    passiveportsmin = null,
                    passiveportsmax = null,
                    localuserbw = null,
                    localuserdlbw = null,
                    anonuserbw = null,
                    anonuserdlbw = null,
                    tls = null,
                    tls_policy = null,
                    tls_opt_allow_client_renegotiations = null,
                    tls_opt_allow_dot_login = null,
                    tls_opt_allow_per_user = null,
                    tls_opt_common_name_required = null,
                    tls_opt_enable_diags = null,
                    tls_opt_export_cert_data = null,
                    tls_opt_no_cert_request = null,
                    tls_opt_no_empty_fragments = null,
                    tls_opt_no_session_reuse_required = null,
                    tls_opt_stdenvvars = null,
                    tls_opt_dns_name_required = null,
                    tls_opt_ip_address_required = null,
                    ssltls_certificate = null,
                    options = null

            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

}