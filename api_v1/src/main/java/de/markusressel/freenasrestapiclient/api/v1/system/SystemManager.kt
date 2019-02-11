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

package de.markusressel.freenasrestapiclient.api.v1.system

import de.markusressel.freenasrestapiclient.api.v1.RequestManager
import de.markusressel.freenasrestapiclient.api.v1.system.alert.AlertApi
import de.markusressel.freenasrestapiclient.api.v1.system.alert.AlertHandler
import de.markusressel.freenasrestapiclient.api.v1.system.update.UpdateApi
import de.markusressel.freenasrestapiclient.api.v1.system.update.UpdateHandler

/**
 * Created by Markus on 09.02.2018.
 */
class SystemManager(private val requestManager: RequestManager,
                    alertApi: AlertApi = AlertHandler(requestManager),
                    updateApi: UpdateApi = UpdateHandler(requestManager)) : AlertApi by alertApi,
        UpdateApi by updateApi, SystemApi {

    override fun getSoftwareVersion(): Single<SoftwareVersionModel> {
        return requestManager.doRequest("/system/version/", Method.GET, SoftwareVersionModel.SingleDeserializer())
    }

    override fun rebootSystem(): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/system/reboot/", Method.POST)
    }

    override fun shutdownSystem(): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/system/shutdown/", Method.POST)
    }

}