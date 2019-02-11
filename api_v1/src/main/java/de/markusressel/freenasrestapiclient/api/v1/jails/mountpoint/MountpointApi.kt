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

package de.markusressel.freenasrestapiclient.api.v1.jails.mountpoint

import de.markusressel.freenasrestapiclient.api.v1.RequestManager

interface MountpointApi {
    /**
     * Get a list of all jail mountpoints
     */
    fun getMountpoints(limit: Int = RequestManager.DEFAULT_LIMIT,
                       offset: Int = RequestManager.DEFAULT_OFFSET): Single<List<MountpointModel>>

    /**
     * Create a new mountpoint
     */
    fun createMountpoint(data: MountpointModel): Single<MountpointModel>

    /**
     * Update an existing mountpoint
     */
    fun updateMountpoint(data: MountpointModel): Single<MountpointModel>

    /**
     * Delete a mountpoint
     */
    fun deleteMountpoint(
            mountpoint: MountpointModel): Single<Pair<Response, ByteArray>>
}