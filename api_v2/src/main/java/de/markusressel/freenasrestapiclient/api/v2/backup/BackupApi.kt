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
import de.markusressel.freenasrestapiclient.api.v2.backup.azure.AzureBackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.b2.B2BackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.credential.BackupCredentialsApi
import de.markusressel.freenasrestapiclient.api.v2.backup.gcs.GcsBackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.s3.S3BackupApi

interface BackupApi : AzureBackupApi, B2BackupApi, BackupCredentialsApi, GcsBackupApi, S3BackupApi {

    /**
     * Creates a backup
     */
    suspend fun createBackup(description: String?,
                             direction: BackupApiImpl.BackupDirection?,
                             transferMode: BackupApiImpl.BackupTransferMode,
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
                             enabled: Boolean?): Result<JsonElement, Exception>

    /**
     * Queries a list of backups
     */
    suspend fun getBackups(): Result<JsonElement, Exception>

    /**
     * Updates a backup
     *
     * @param id id of the backup
     */
    suspend fun updateBackup(id: String,
                             description: String?,
                             direction: BackupApiImpl.BackupDirection?,
                             transferMode: BackupApiImpl.BackupTransferMode,
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
                             enabled: Boolean?): Result<JsonElement, Exception>

    /**
     * Deletes a backup
     *
     * @param id id of the backup
     */
    suspend fun deleteBackup(id: String): Result<JsonElement, Exception>

}