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

package de.markusressel.freenasrestapiclient.api.v1.sharing.nfs

import de.markusressel.freenasrestapiclient.api.v1.RequestManager.Companion.DEFAULT_LIMIT
import de.markusressel.freenasrestapiclient.api.v1.RequestManager.Companion.DEFAULT_OFFSET

/**
 * Created by Markus on 10.02.2018.
 */
interface NfsApi {

    /**
     * Get a list of all NFS shares
     */
    fun getNfsShares(limit: Int = DEFAULT_LIMIT,
                     offset: Int = DEFAULT_OFFSET): Single<List<NfsShareModel>>

    /**
     * Create a new NFS share
     */
    fun createNfsShare(data: NfsShareModel): Single<NfsShareModel>

    /**
     * Update an existing NFS share
     */
    fun updateNfsShare(data: NfsShareModel): Single<NfsShareModel>

    /**
     * Delete an existing NFS share
     */
    fun deleteNfsShare(data: NfsShareModel): Single<Pair<Response, ByteArray>>

}