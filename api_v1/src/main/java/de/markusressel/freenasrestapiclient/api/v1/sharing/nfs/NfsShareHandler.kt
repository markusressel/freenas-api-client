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

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import de.markusressel.freenasrestapiclient.api.v1.RequestManager
import io.reactivex.Single

/**
 * Created by Markus on 10.02.2018.
 */
class NfsShareHandler(private val requestManager: RequestManager) : NfsApi {

    override fun getNfsShares(limit: Int, offset: Int): Single<List<NfsShareModel>> {
        val params = requestManager.createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/sharing/nfs/", params, Method.GET, NfsShareModel.ListDeserializer())
    }

    override fun createNfsShare(data: NfsShareModel): Single<NfsShareModel> {
        return requestManager.doJsonRequest("/sharing/nfs/", Method.POST, data, NfsShareModel.SingleDeserializer())
    }

    override fun updateNfsShare(data: NfsShareModel): Single<NfsShareModel> {
        return requestManager.doJsonRequest("/sharing/nfs/${data.id}/", Method.PUT, data, NfsShareModel.SingleDeserializer())
    }

    override fun deleteNfsShare(data: NfsShareModel): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/sharing/nfs/${data.id}/", Method.DELETE)
    }
}