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

package de.markusressel.freenasrestapiclient.api.v2.activedirectory

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class ActiveDirectoryApiImpl(val websocketApiClient: WebsocketApiClient) : ActiveDirectoryApi {

    override suspend fun activeDirectoryChangeTrustAccountPw(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.change_trust_account_pw")
    }

    override suspend fun getActiveDirectoryConfig(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.config")
    }

    override suspend fun getActiveDirectoryDomainInfo(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.domain_info")
    }

    override suspend fun getActiveDirectorySpnList(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.get_spn_list")
    }

    override suspend fun getActiveDirectoryState(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.get_state")
    }

    override suspend fun getActiveDirectoryIdmapBackendChoices(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.idmap_backend_choices")
    }

    override suspend fun leaveActiveDirectory(username: String, password: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.leave", jsonObject(
                "username" to username,
                "password" to password
        ))
    }

    override suspend fun getActiveDirectoryNssInfoChoices(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.nss_info_choices")
    }

    override suspend fun getActiveDirectorySaslWrappingChoices(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.sasl_wrapping_choices")
    }

    override suspend fun getActiveDirectorySslChoices(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("activedirectory.ssl_choices")
    }

    override suspend fun updateActiveDirectory(domainName: String, bindName: String, bindPw: String, ssl: ActiveDirectoryApi.SslEnum, certificate: Int?, validate_certificates: Boolean, verbose_logging: Boolean, use_default_domain: Boolean, allow_trusted_doms: Boolean, allow_dns_updates: Boolean, disable_freenas_cache: Boolean, site: String?, kerberos_realm: Int?, kerberos_principal: String?, timeout: Int, dns_timeout: Int, idmap_backend: ActiveDirectoryApi.IdmapBackend, nss_info: ActiveDirectoryApi.NssInfo?, ldap_sasl_wrapping: ActiveDirectoryApi.LdapSaslWrapping, createcomputer: String, netbiosname: String, netbiosname_b: String, netbiosalias: List<Any>, enable: Boolean): Result<JsonElement, Exception> {
        TODO("Not yet implemented")
    }

}