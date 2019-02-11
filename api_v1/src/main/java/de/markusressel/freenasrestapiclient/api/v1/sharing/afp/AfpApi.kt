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

package de.markusressel.freenasrestapiclient.api.v1.sharing.afp

import de.markusressel.freenasrestapiclient.api.v1.RequestManager.Companion.DEFAULT_LIMIT
import de.markusressel.freenasrestapiclient.api.v1.RequestManager.Companion.DEFAULT_OFFSET

/**
 * Created by Markus on 10.02.2018.
 */
interface AfpApi {

    /**
     * Get a list of all AFP shares
     */
    fun getAfpShares(limit: Int = DEFAULT_LIMIT,
                     offset: Int = DEFAULT_OFFSET): Single<List<AfpShareModel>>

    /**
     * Create a new AFP share
     */
    fun createAfpShare(data: AfpShareModel): Single<AfpShareModel>

    /**
     * Update an existing AFP share
     */
    fun updateAfpShare(data: AfpShareModel): Single<AfpShareModel>

    /**
     * Delete an existing AFP share
     */
    fun deleteAfpShare(data: AfpShareModel): Single<Pair<Response, ByteArray>>

}