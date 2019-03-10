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

import com.github.kittinunf.result.Result
import de.markusressel.freenasrestapiclient.api.v2.afp.AfpApi
import de.markusressel.freenasrestapiclient.api.v2.afp.AfpApiImpl
import de.markusressel.freenasrestapiclient.api.v2.alert.AlertApi
import de.markusressel.freenasrestapiclient.api.v2.alert.AlertApiImpl
import de.markusressel.freenasrestapiclient.api.v2.auth.AuthApi
import de.markusressel.freenasrestapiclient.api.v2.auth.AuthApiImpl
import de.markusressel.freenasrestapiclient.api.v2.backup.BackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.BackupApiImpl
import de.markusressel.freenasrestapiclient.api.v2.boot.BootApi
import de.markusressel.freenasrestapiclient.api.v2.boot.BootApiImpl
import de.markusressel.freenasrestapiclient.api.v2.bootenv.BootEnvApi
import de.markusressel.freenasrestapiclient.api.v2.bootenv.BootEnvApiImpl
import de.markusressel.freenasrestapiclient.api.v2.certificate.CertificateApi
import de.markusressel.freenasrestapiclient.api.v2.certificate.CertificateApiImpl
import de.markusressel.freenasrestapiclient.api.v2.cloudsync.CloudSyncApi
import de.markusressel.freenasrestapiclient.api.v2.cloudsync.CloudSyncApiImpl
import de.markusressel.freenasrestapiclient.api.v2.cluster.ClusterApi
import de.markusressel.freenasrestapiclient.api.v2.cluster.ClusterApiImpl
import de.markusressel.freenasrestapiclient.api.v2.config.ConfigApi
import de.markusressel.freenasrestapiclient.api.v2.config.ConfigApiImpl
import de.markusressel.freenasrestapiclient.api.v2.cronjob.CronjobApi
import de.markusressel.freenasrestapiclient.api.v2.cronjob.CronjobApiImpl
import de.markusressel.freenasrestapiclient.api.v2.device.DeviceApi
import de.markusressel.freenasrestapiclient.api.v2.device.DeviceApiImpl
import de.markusressel.freenasrestapiclient.api.v2.disk.DiskApi
import de.markusressel.freenasrestapiclient.api.v2.disk.DiskApiImpl
import de.markusressel.freenasrestapiclient.api.v2.dns.DnsApi
import de.markusressel.freenasrestapiclient.api.v2.dns.DnsApiImpl
import de.markusressel.freenasrestapiclient.api.v2.domaincontroller.DomainControllerApi
import de.markusressel.freenasrestapiclient.api.v2.domaincontroller.DomainControllerApiImpl
import de.markusressel.freenasrestapiclient.api.v2.dyndns.DynDnsApi
import de.markusressel.freenasrestapiclient.api.v2.dyndns.DynDnsApiImpl
import de.markusressel.freenasrestapiclient.api.v2.filesystem.FilesystemApi
import de.markusressel.freenasrestapiclient.api.v2.filesystem.FilesystemApiImpl
import de.markusressel.freenasrestapiclient.api.v2.ftp.FtpApi
import de.markusressel.freenasrestapiclient.api.v2.ftp.FtpApiImpl
import de.markusressel.freenasrestapiclient.api.v2.group.GroupApi
import de.markusressel.freenasrestapiclient.api.v2.group.GroupApiImpl
import de.markusressel.freenasrestapiclient.api.v2.interfaces.InterfacesApi
import de.markusressel.freenasrestapiclient.api.v2.interfaces.InterfacesApiImpl
import de.markusressel.freenasrestapiclient.api.v2.jail.JailApi
import de.markusressel.freenasrestapiclient.api.v2.jail.JailApiImpl
import de.markusressel.freenasrestapiclient.api.v2.sharing.SharingApi
import de.markusressel.freenasrestapiclient.api.v2.sharing.SharingApiImpl
import de.markusressel.freenasrestapiclient.api.v2.update.UpdateApi
import de.markusressel.freenasrestapiclient.api.v2.update.UpdateApiImpl
import de.markusressel.freenasrestapiclient.core.BasicAuthConfig

/**
 * Convenience delegation class for easy access to all api methods
 *
 * Created by Markus on 06.02.2018.
 */
open class FreeNasRestApiV2Client(baseUrl: String, auth: BasicAuthConfig,
                                  val websocketClient: WebsocketApiClient = WebsocketApiClient(baseUrl, auth),
                                  afpApi: AfpApi = AfpApiImpl(websocketClient),
                                  alertApi: AlertApi = AlertApiImpl(websocketClient),
                                  authApi: AuthApi = AuthApiImpl(websocketClient),
                                  backupApi: BackupApi = BackupApiImpl(websocketClient),
                                  bootApi: BootApi = BootApiImpl(websocketClient),
                                  bootEnvApi: BootEnvApi = BootEnvApiImpl(websocketClient),
                                  certificateApi: CertificateApi = CertificateApiImpl(websocketClient),
                                  cloudSyncApi: CloudSyncApi = CloudSyncApiImpl(websocketClient),
                                  clusterApi: ClusterApi = ClusterApiImpl(websocketClient),
                                  configApi: ConfigApi = ConfigApiImpl(websocketClient),
                                  cronjobApi: CronjobApi = CronjobApiImpl(websocketClient),
                                  deviceApi: DeviceApi = DeviceApiImpl(websocketClient),
                                  diskApi: DiskApi = DiskApiImpl(websocketClient),
                                  dnsApi: DnsApi = DnsApiImpl(websocketClient),
                                  domainControllerApi: DomainControllerApi = DomainControllerApiImpl(websocketClient),
                                  dynDnsApi: DynDnsApi = DynDnsApiImpl(websocketClient),
                                  filesystemApi: FilesystemApi = FilesystemApiImpl(websocketClient),
                                  ftpApi: FtpApi = FtpApiImpl(websocketClient),
                                  groupApi: GroupApi = GroupApiImpl(websocketClient),
                                  interfacesApi: InterfacesApi = InterfacesApiImpl(websocketClient),
                                  jailApi: JailApi = JailApiImpl(websocketClient),
                                  sharingApi: SharingApi = SharingApiImpl(websocketClient),
                                  updateApi: UpdateApi = UpdateApiImpl(websocketClient)) :
        AfpApi by afpApi,
        AlertApi by alertApi,
        AuthApi by authApi,
        BackupApi by backupApi,
        BootApi by bootApi,
        BootEnvApi by bootEnvApi,
        CertificateApi by certificateApi,
        CloudSyncApi by cloudSyncApi,
        ClusterApi by clusterApi,
        ConfigApi by configApi,
        CronjobApi by cronjobApi,
        DeviceApi by deviceApi,
        DiskApi by diskApi,
        DnsApi by dnsApi,
        DomainControllerApi by domainControllerApi,
        DynDnsApi by dynDnsApi,
        FilesystemApi by filesystemApi,
        FtpApi by ftpApi,
        GroupApi by groupApi,
        InterfacesApi by interfacesApi,
        JailApi by jailApi,
        SharingApi by sharingApi,
        UpdateApi by updateApi {

    /**
     * Connect to the websocket api
     *
     * @param listener optional connection status listener
     */
    suspend fun connect(listener: WebsocketConnectionListener? = null): Result<Boolean, Exception> {
        websocketClient.setListener(listener)
        return websocketClient.connect()
    }

    /**
     * Disconnect the api client
     *
     * @param code the code indicating the reason
     * @param reason a text description of the disconnect reason
     */
    fun disconnect(code: Int = 1000, reason: String = "") {
        websocketClient.disconnect(code, reason)
    }

}