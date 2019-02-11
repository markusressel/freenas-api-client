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

package de.markusressel.freenasrestapiclient.api.v1.jails.jail

import de.markusressel.freenasrestapiclient.api.v1.RequestManager

/**
 * Created by Markus on 09.02.2018.
 */
class JailHandler(private val requestManager: RequestManager) : JailApi {

    override fun getJails(limit: Int, offset: Int): Single<List<JailModel>> {
        val params = requestManager.createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/jails/jails/", params, Method.GET, JailModel.ListDeserializer())
    }

    override fun createJail(data: JailModel): Single<JailModel> {
        return requestManager.doJsonRequest("/jails/jails/", Method.POST, data, JailModel.SingleDeserializer())
    }

    override fun startJail(jailId: Long): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/jails/jails/$jailId/start/", Method.POST)
    }

    override fun stopJail(jailId: Long): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/jails/jails/$jailId/stop/", Method.POST)
    }

    override fun restartJail(jailId: Long): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/jails/jails/$jailId/restart/", Method.POST)
    }

    override fun deleteJail(jailId: Long): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/jails/jails/$jailId/", Method.DELETE)
    }

}