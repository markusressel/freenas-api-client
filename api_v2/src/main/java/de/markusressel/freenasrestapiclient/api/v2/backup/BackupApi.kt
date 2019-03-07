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

import de.markusressel.freenasrestapiclient.api.v2.ApiListener
import de.markusressel.freenasrestapiclient.api.v2.backup.azure.AzureBackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.b2.B2BackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.credential.BackupCredentialsApi
import de.markusressel.freenasrestapiclient.api.v2.backup.gcs.GcsBackupApi
import de.markusressel.freenasrestapiclient.api.v2.backup.s3.S3BackupApi

interface BackupApi : AzureBackupApi, B2BackupApi, BackupCredentialsApi, GcsBackupApi, S3BackupApi {

    /**
     * Creates a backup
     *
     * TODO many parameters
     *
     * @param listener response listener
     */
    fun createBackup(listener: ApiListener)

    /**
     * Queries a list of backups
     */
    fun getBackups(listener: ApiListener)

    /**
     * Updates a backup
     *
     * @param id id of the backup
     */
    fun updateBackup(id: String, listener: ApiListener)

    /**
     * Deletes a backup
     *
     * @param id id of the backup
     * @param listener response listener
     */
    fun deleteBackup(id: String, listener: ApiListener)

}