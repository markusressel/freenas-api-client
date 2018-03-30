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

package de.markusressel.freenasrestapiclient.library.storage.snapshot

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import de.markusressel.freenasrestapiclient.library.RequestManager
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class SnapshotHandler(private val requestManager: RequestManager) : SnapshotApi {

    override fun getSnapshots(limit: Int, offset: Int): Single<List<SnapshotModel>> {
        val params = requestManager
                .createLimitOffsetParams(limit, offset)
        return requestManager
                .doRequest("/storage/snapshot/", params, Method.GET,
                        SnapshotModel.ListDeserializer())
    }

    override fun createSnapshot(dataset: String, name: String,
                                recursive: Boolean): Single<SnapshotModel> {
        val data = jsonObject("dataset" to dataset, "name" to name, "recursive" to recursive)
        return requestManager
                .doJsonRequest("/storage/snapshot/", Method.POST, data,
                        SnapshotModel.SingleDeserializer())
    }

    override fun deleteSnapshot(
            snapshotId: Long): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/storage/snapshot/$snapshotId/", Method.DELETE)
    }

    override fun cloneSnapshot(snapshotId: Long,
                               cloneName: String): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        val data = jsonObject("name" to cloneName)
        return requestManager
                .doJsonRequest("/storage/snapshot/$snapshotId/clone/", Method.POST, data)
    }

    override fun rollbackSnapshot(snapshotId: Long,
                                  force: Boolean): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        val data = jsonObject("force" to force)
        return requestManager
                .doJsonRequest("/storage/snapshot/$snapshotId/rollback/", Method.POST, data)
    }

}