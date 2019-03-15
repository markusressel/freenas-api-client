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

package de.markusressel.freenasrestapiclient.api.v2.cloudsync

interface CloudSyncApi : CloudSyncCredentialsApi {

    enum class Direction {
        PUSH,
        PULL
    }

    enum class TransferMode {
        SYNC,
        COPY,
        MOVE
    }

    data class Schedule(val minute: String,
                        val hour: String,
                        val dom: String,
                        val month: String,
                        val dow: String)

    /**
     * Creates a new cloud sync entry
     */
    suspend fun createCloudSync(
            description: String,
            direction: Direction,
            transfer_mode: TransferMode,
            path: String,
            credentials: Int,
            encryption: Boolean,
            filename_encryption: Boolean,
            encryption_password: String,
            encryption_salt: String,
            schedule: Schedule,
            args: String,
            enabled: Boolean
    )

    /**
     * Deletes a cloud sync entry
     *
     * @param id entry id
     */
    suspend fun deleteCloudSync(id: Int)

    /**
     * List cloud sync buckets
     */
    suspend fun listBuckets(id: Int)

    suspend fun listDirectories()

    suspend fun cloudSyncProviders()

    /**
     * Queries a list of cloud sync entries
     *
     * TODO: query params
     */
    suspend fun getCloudSyncEntries()

    /**
     * Run the cloud_sync job [id], syncing the local data to remote.
     *
     * @param id
     */
    suspend fun runCloudSync(id: Int)

    /**
     * Updates a cloud sync entry
     *
     * @param id id of cloud sync entry
     */
    suspend fun updateCloudSync(id: Int)

}