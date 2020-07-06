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
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum

interface ActiveDirectoryApi {

    enum class IdmapBackend : ApiEnum {
        @SerializedName("AD")
        AD,

        @SerializedName("AUTORID")
        AUTORID,

        @SerializedName("FRUIT")
        FRUIT,

        @SerializedName("LDAP")
        LDAP,

        @SerializedName("NSS")
        NSS,

        @SerializedName("RFC2307")
        RFC2307,

        @SerializedName("RID")
        RID,

        @SerializedName("SCRIPT")
        SCRIPT
    }

    enum class SslEnum : ApiEnum {
        @SerializedName("OFF")
        OFF,

        @SerializedName("ON")
        ON,

        @SerializedName("START_TLS")
        START_TLS
    }

    enum class NssInfo : ApiEnum {
        @SerializedName("SFU")
        SFU,

        @SerializedName("SFU20")
        SFU20,

        @SerializedName("RFC2307")
        RFC2307
    }

    enum class LdapSaslWrapping : ApiEnum {
        @SerializedName("PLAIN")
        PLAIN,

        @SerializedName("SIGN")
        SIGN,

        @SerializedName("SEAL")
        SEAL
    }

    /**
     * Force an update of the AD machine account password.
     * This can be used to refresh the Kerberos principals in the server's system keytab.
     */
    suspend fun activeDirectoryChangeTrustAccountPw(): Result<JsonElement, Exception>

    /**
     * Get ActiveDirectory config
     */
    suspend fun getActiveDirectoryConfig(): Result<JsonElement, Exception>

    /**
     * Get ActiveDirectory domain info
     *
     * Returns the following information about the currently joined domain:
     * LDAP server IP address of current LDAP server to which TrueNAS is connected.
     * LDAP server name DNS name of LDAP server to which TrueNAS is connected
     * Realm Kerberos realm
     * LDAP port
     * Server time timestamp.
     * KDC server Kerberos KDC to which TrueNAS is connected
     * Server time offset current time offset from DC.
     * Last machine account password change. timestamp
     */
    suspend fun getActiveDirectoryDomainInfo(): Result<JsonElement, Exception>

    /**
     * Return list of kerberos SPN entries registered for the server's Active Directory
     * computer account. This may not reflect the state of the server's current kerberos keytab.
     */
    suspend fun getActiveDirectorySpnList(): Result<JsonElement, Exception>

    /**
     * Wrapper function for 'directoryservices.get_state'.
     * Returns only the state of the Active Directory service.
     */
    suspend fun getActiveDirectoryState(): Result<JsonElement, Exception>

    /**
     * Returns list of available idmap backends.
     */
    suspend fun getActiveDirectoryIdmapBackendChoices(): Result<JsonElement, Exception>

    /**
     * Leave Active Directory domain. This will remove computer object from AD and
     * clear relevant configuration data from the NAS. This requires credentials
     * for appropriately-privileged user. Credentials are used to obtain a
     * kerberos ticket, which is used to perform the actual removal from the domain.
     */
    suspend fun leaveActiveDirectory(username: String, password: String): Result<JsonElement, Exception>

    /**
     * Returns list of available LDAP schema choices.
     */
    suspend fun getActiveDirectoryNssInfoChoices(): Result<JsonElement, Exception>

    /**
     * Returns list of sasl wrapping choices.
     */
    suspend fun getActiveDirectorySaslWrappingChoices(): Result<JsonElement, Exception>

    /**
     * Returns list of SSL choices.
     */
    suspend fun getActiveDirectorySslChoices(): Result<JsonElement, Exception>

    /**
     * Update active directory configuration. domainname full DNS domain name of the Active Directory domain.
     *
     * bindname username used to perform the intial domain join.
     * bindpw password used to perform the initial domain join. User- provided credentials are used to obtain a kerberos ticket, which is used to perform the actual domain join.
     * ssl establish SSL/TLS-protected connections to the DCs in the Active Directory domain.
     * certificate LDAPs client certificate to be used for certificate- based authentication in the AD domain. If certificate-based authentication is not configured, SASL GSSAPI binds will be performed.
     * validate_certificates specifies whether to perform checks on server certificates in a TLS session. If enabled, TLS_REQCERT demand is set. The server certificate is requested. If no certificate is provided or if a bad certificate is provided, the session is immediately terminated. If disabled, TLS_REQCERT allow is set. The server certificate is requested, but all errors are ignored.
     * verbose_logging increase logging during the domain join process.
     * use_default_domain controls whether domain users and groups have the pre-windows 2000 domain name prepended to the user account. When enabled, the user appears as "administrator" rather than "EXAMPLEdministrator"
     * allow_trusted_doms enable support for trusted domains. If this parameter is enabled, then separate idmap backends must be configured for each trusted domain, and the idmap cache should be cleared.
     * allow_dns_updates during the domain join process, automatically generate DNS entries in the AD domain for the NAS. If this is disabled, then a domain administrator must manually add appropriate DNS entries for the NAS. This parameter is recommended for TrueNAS HA servers.
     * disable_freenas_cache disables active caching of AD users and groups. When disabled, only users cached in winbind's internal cache are visible in GUI dropdowns. Disabling active caching is recommended in environments with a large amount of users.
     * site AD site of which the NAS is a member. This parameter is auto- detected during the domain join process. If no AD site is configured for the subnet in which the NAS is configured, then this parameter appears as 'Default-First-Site-Name'. Auto-detection is only performed during the initial domain join.
     * kerberos_realm in which the server is located. This parameter is automatically populated during the initial domain join. If the NAS has an AD site configured and that site has multiple kerberos servers, then the kerberos realm is automatically updated with a site-specific configuration to use those servers. Auto-detection is only performed during initial domain join.
     * kerberos_principal kerberos principal to use for AD-related operations outside of Samba. After intial domain join, this field is updated with the kerberos principal associated with the AD machine account for the NAS.
     * nss_info controls how Winbind retrieves Name Service Information to construct a user's home directory and login shell. This parameter is only effective if the Active Directory Domain Controller supports the Microsoft Services for Unix (SFU) LDAP schema.
     * timeout timeout value for winbind-related operations. This value may need to be increased in environments with high latencies for communications with domain controllers or a large number of domain controllers. Lowering the value may cause status checks to fail.
     * dns_timeout timeout value for DNS queries during the initial domain join. This value is also set as the NETWORK_TIMEOUT in the ldap config file.
     * ldap_sasl_wrapping defines whether ldap traffic will be signed or signed and encrypted (sealed). LDAP traffic that does not originate from Samba defaults to using GSSAPI signing unless it is tunnelled over LDAPs.
     * createcomputer Active Directory Organizational Unit in which new computer accounts are created.
     * The OU string is read from top to bottom without RDNs. Slashes ("/") are used as delimiters, like Computers/Servers/NAS. The backslash ("\") is used to escape characters but not as a separator. Backslashes are interpreted at multiple levels and might require doubling or even quadrupling to take effect.
     * When this field is blank, new computer accounts are created in the Active Directory default OU.
     * v    idmap_backend provides a plugin interface for Winbind to use varying backends to store SID/uid/gid mapping tables. The correct setting depends on the environment in which the NAS is deployed.
     * The Active Directory service is started after a configuration update if the service was initially disabled, and the updated configuration sets enable to True. The Active Directory service is stopped if enable is changed to False. If the configuration is updated, but the initial enable state is True, and remains unchanged, then the samba server is only restarted.
     * During the domain join, a kerberos keytab for the newly-created AD machine account is generated. It is used for all future LDAP / AD interaction and the user-provided credentials are removed.
     */
    suspend fun updateActiveDirectory(domainName: String,
                                      bindName: String,
                                      bindPw: String,
                                      ssl: SslEnum,
                                      certificate: Int?,
                                      validate_certificates: Boolean,
                                      verbose_logging: Boolean,
                                      use_default_domain: Boolean,
                                      allow_trusted_doms: Boolean,
                                      allow_dns_updates: Boolean,
                                      disable_freenas_cache: Boolean,
                                      site: String?,
                                      kerberos_realm: Int?,
                                      kerberos_principal: String?,
                                      timeout: Int,
                                      dns_timeout: Int,
                                      idmap_backend: IdmapBackend,
                                      nss_info: NssInfo?,
                                      ldap_sasl_wrapping: LdapSaslWrapping,
                                      createcomputer: String,
                                      netbiosname: String,
                                      netbiosname_b: String,
                                      netbiosalias: List<Any>,
                                      enable: Boolean): Result<JsonElement, Exception>

}