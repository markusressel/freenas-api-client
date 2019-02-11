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

package de.markusressel.freenasrestapiclient.api.v1.services.service

import de.markusressel.freenasrestapiclient.api.v1.RequestManager

/**
 * Created by Markus on 09.02.2018.
 */
class ServiceHandler(private val requestManager: RequestManager) : ServiceApi {

    override fun getServices(limit: Int, offset: Int): Single<List<ServiceModel>> {
        val params = requestManager
                .createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/services/services/", params, Method.GET, ServiceModel.ListDeserializer())
    }

    override fun updateService(serviceId: Int, srv_service: String,
                               srv_enable: Boolean): Single<ServiceModel> {
        val data = jsonObject("srv_service" to srv_service, "srv_enable" to srv_enable)
        return requestManager.doJsonRequest("/services/services/$serviceId/", Method.PUT, data, ServiceModel.SingleDeserializer())
    }

}