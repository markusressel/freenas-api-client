/*
 * Copyright (c) 2018 Markus Ressel
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

package de.markusressel.freenasrestapiclient.api.v1.storage.snapshot

import de.markusressel.freenasrestapiclient.api.v1.RequestManager

interface SnapshotApi {
    /**
     * Get a list of all snapshots
     */
    fun getSnapshots(limit: Int = RequestManager.DEFAULT_LIMIT,
                     offset: Int = RequestManager.DEFAULT_OFFSET): Single<List<SnapshotModel>>

    /**
     * Create a new snapshot
     *
     * @param dataset name of dataset to snapshot
     * @param name name of the snapshot
     * @param recursive True if you want it to recursively snapshot the dataset
     */
    fun createSnapshot(dataset: String, name: String,
                       recursive: Boolean = true): Single<SnapshotModel>

    /**
     * Delete a snapshot
     */
    fun deleteSnapshot(snapshotId: Long): Single<Pair<Response, ByteArray>>

    /**
     * Delete a snapshot
     */
    fun cloneSnapshot(snapshotId: Long,
                      cloneName: String): Single<Pair<Response, ByteArray>>

    /**
     * Rollback a snapshot
     */
    fun rollbackSnapshot(snapshotId: Long,
                         force: Boolean): Single<Pair<Response, ByteArray>>
}