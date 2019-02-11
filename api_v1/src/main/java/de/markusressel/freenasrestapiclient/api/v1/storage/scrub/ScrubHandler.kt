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

package de.markusressel.freenasrestapiclient.api.v1.storage.scrub

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import de.markusressel.freenasrestapiclient.api.v1.RequestManager
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class ScrubHandler(private val requestManager: RequestManager) : ScrubApi {

    override fun getScrubs(limit: Int, offset: Int): Single<List<ScrubModel>> {
        val params = requestManager.createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/storage/scrub/", params, Method.GET, ScrubModel.ListDeserializer())
    }

    override fun createScrub(data: ScrubModel): Single<ScrubModel> {
        return requestManager.doJsonRequest("/storage/scrub/", Method.POST, data, ScrubModel.SingleDeserializer())
    }

    override fun updateScrub(data: ScrubModel): Single<ScrubModel> {
        return requestManager.doJsonRequest("/storage/scrub/${data.id}/", Method.PUT, data, ScrubModel.SingleDeserializer())
    }

    override fun deleteScrub(data: ScrubModel): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/storage/scrub/${data.id}/", Method.DELETE)
    }

}