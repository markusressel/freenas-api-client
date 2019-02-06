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

package de.markusressel.freenasrestapiclient.library.jails.template

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.jails.mountpoint.MountpointModel
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class TemplateHandler(private val requestManager: RequestManager) : TemplateApi {

    override fun getTemplates(limit: Int, offset: Int): Single<List<TemplateModel>> {
        val params = requestManager
                .createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/jails/templates/", params, Method.GET, TemplateModel.ListDeserializer())
    }

    override fun createTemplate(jt_arch: String, jt_instances: Long, jt_name: String, jt_os: String,
                                jt_url: String): Single<TemplateModel> {
        val data = TemplateModel(0, jt_arch, jt_instances, jt_name, jt_os, jt_url)
        return requestManager.doJsonRequest("/jails/templates/", Method.POST, data, TemplateModel.SingleDeserializer())
    }

    override fun updateTemplate(id: Long, jt_arch: String, jt_instances: Long, jt_name: String,
                                jt_os: String, jt_url: String): Single<MountpointModel> {
        val data = TemplateModel(id, jt_arch, jt_instances, jt_name, jt_os, jt_url)
        return requestManager.doJsonRequest("/jails/templates/$id/", Method.PUT, data, MountpointModel.SingleDeserializer())
    }

    override fun deleteTemplate(templateId: Long): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/jails/templates/$templateId/", Method.DELETE)
    }

}