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

package de.markusressel.freenasrestapiclient.library.sharing.afp

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import de.markusressel.freenasrestapiclient.library.RequestManager
import io.reactivex.Single

/**
 * Created by Markus on 10.02.2018.
 */
class AfpShareHandler(private val requestManager: RequestManager) : AfpApi {

    override fun getAfpShares(limit: Int, offset: Int): Single<List<AfpShareModel>> {
        val params = requestManager
                .createLimitOffsetParams(limit, offset)
        return requestManager
                .doRequest("/sharing/afp/", params, Method.GET, AfpShareModel.ListDeserializer())
    }

    override fun createAfpShare(data: AfpShareModel): Single<AfpShareModel> {
        return requestManager
                .doJsonRequest("/sharing/afp/", Method.POST, data,
                        AfpShareModel.SingleDeserializer())
    }

    override fun updateAfpShare(data: AfpShareModel): Single<AfpShareModel> {
        return requestManager
                .doJsonRequest("/sharing/afp/${data.id}/", Method.PUT, data,
                        AfpShareModel.SingleDeserializer())
    }

    override fun deleteAfpShare(
            data: AfpShareModel): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/sharing/afp/${data.id}/", Method.DELETE)
    }
}