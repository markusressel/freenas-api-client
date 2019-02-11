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

package de.markusressel.freenasrestapiclient.api.v1.tasks.smart

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import de.markusressel.freenasrestapiclient.core.RequestManager
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class SMARTTaskHandler(private val requestManager: RequestManager) : SMARTTaskApi {
    override fun getSMARTTasks(limit: Int, offset: Int): Single<List<SMARTTaskModel>> {
        val params = requestManager.createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/tasks/smarttest/", params, Method.GET, SMARTTaskModel.ListDeserializer())
    }

    override fun createSMARTTask(data: SMARTTaskModel): Single<SMARTTaskModel> {
        return requestManager.doJsonRequest("/tasks/smarttest/", Method.POST, data, SMARTTaskModel.SingleDeserializer())
    }

    override fun updateSMARTTask(data: SMARTTaskModel): Single<SMARTTaskModel> {
        return requestManager.doJsonRequest("/tasks/smarttest/${data.id}/", Method.PUT, data, SMARTTaskModel.SingleDeserializer())
    }

    override fun deleteSMARTTask(data: SMARTTaskModel): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/tasks/smarttest/${data.id}/", Method.DELETE)
    }

}