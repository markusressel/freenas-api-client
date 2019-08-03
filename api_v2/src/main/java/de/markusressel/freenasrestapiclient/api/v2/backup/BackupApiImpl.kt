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

package de.markusressel.freenasrestapiclient.api.v2.backup

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient
import de.markusressel.freenasrestapiclient.api.v2.backup.azure.AzureBackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.azure.AzureBackupApiImpl
import de.markusressel.freenasrestapiclient.api.v2.backup.b2.B2BackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.b2.B2BackupApiImpl
import de.markusressel.freenasrestapiclient.api.v2.backup.credential.BackupCredentialsApi
import de.markusressel.freenasrestapiclient.api.v2.backup.credential.BackupCredentialsApiImpl
import de.markusressel.freenasrestapiclient.api.v2.backup.gcs.GcsBackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.gcs.GcsBackupApiImpl
import de.markusressel.freenasrestapiclient.api.v2.backup.s3.S3BackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.s3.S3BackupApiImpl

class BackupApiImpl(val websocketApiClient: WebsocketApiClient,
                    azureBackupApi: AzureBackupApi = AzureBackupApiImpl(websocketApiClient),
                    b2BackupApi: B2BackupApi = B2BackupApiImpl(websocketApiClient),
                    backupCredentialsApi: BackupCredentialsApi = BackupCredentialsApiImpl(websocketApiClient),
                    gcsBackupApi: GcsBackupApi = GcsBackupApiImpl(websocketApiClient),
                    s3BackupApi: S3BackupApi = S3BackupApiImpl(websocketApiClient)
) : BackupApi,
        AzureBackupApi by azureBackupApi,
        B2BackupApi by b2BackupApi,
        BackupCredentialsApi by backupCredentialsApi,
        GcsBackupApi by gcsBackupApi,
        S3BackupApi by s3BackupApi {

    enum class BackupDirection : ApiEnum {
        PUSH,
        PULL;
    }

    enum class BackupTransferMode : ApiEnum {
        SYNC,
        COPY,
        MOVE;
    }

    override suspend fun createBackup(description: String?,
                                      direction: BackupDirection?,
                                      transferMode: BackupTransferMode,
                                      path: String?,
                                      credential: Int,
                                      encryption: Boolean?,
                                      filenameEncryption: Boolean?,
                                      encryptionPassword: String?,
                                      encryptionSalt: String?,
                                      minute: String?,
                                      hour: String?,
                                      daymonth: String?,
                                      dayweek: String?,
                                      month: String?,
                                      enabled: Boolean?): Result<JsonElement, Exception> {
        val args = mapOf(
                "description" to description,
                "direction" to direction,
                "transfer_mode" to transferMode,
                "path" to path,
                "credential" to credential,
                "encryption" to encryption,
                "filename_encryption" to filenameEncryption,
                "encryption_password" to encryptionPassword,
                "encryption_salt" to encryptionSalt,
                "minute" to minute,
                "hour" to hour,
                "daymonth" to daymonth,
                "dayweek" to dayweek,
                "month" to month,
                "enabled" to enabled
        )
        return websocketApiClient.callMethod("backup.create", args)
    }

    override suspend fun getBackups(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("backup.query")
    }

    override suspend fun updateBackup(id: String,
                                      description: String?,
                                      direction: BackupDirection?,
                                      transferMode: BackupTransferMode,
                                      path: String?,
                                      credential: Int,
                                      encryption: Boolean?,
                                      filenameEncryption: Boolean?,
                                      encryptionPassword: String?,
                                      encryptionSalt: String?,
                                      minute: String?,
                                      hour: String?,
                                      daymonth: String?,
                                      dayweek: String?,
                                      month: String?,
                                      enabled: Boolean?): Result<JsonElement, Exception> {

        val args = mapOf(
                "description" to description,
                "direction" to direction,
                "transfer_mode" to transferMode,
                "path" to path,
                "credential" to credential,
                "encryption" to encryption,
                "filename_encryption" to filenameEncryption,
                "encryption_password" to encryptionPassword,
                "encryption_salt" to encryptionSalt,
                "minute" to minute,
                "hour" to hour,
                "daymonth" to daymonth,
                "dayweek" to dayweek,
                "month" to month,
                "enabled" to enabled
        )
        return websocketApiClient.callMethod("backup.update", id, args)
    }

    override suspend fun deleteBackup(id: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("backup.delete", id)
    }
}