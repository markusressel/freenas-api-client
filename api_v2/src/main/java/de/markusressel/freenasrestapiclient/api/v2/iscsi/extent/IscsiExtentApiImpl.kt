/*
 * Copyright (c) 2019 Markus Ressel
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

package de.markusressel.freenasrestapiclient.api.v2.iscsi.extent

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient
import de.markusressel.freenasrestapiclient.api.v2.iscsi.extent.IscsiExtentApi.ExtentRpm
import de.markusressel.freenasrestapiclient.api.v2.iscsi.extent.IscsiExtentApi.ExtentType

class IscsiExtentApiImpl(val websocketApiClient: WebsocketApiClient) : IscsiExtentApi {
    override suspend fun createIscsiExtent(name: String,
                                           type: ExtentType,
                                           disk: String?,
                                           serial: String?,
                                           path: String?,
                                           filesize: Int?,
                                           blocksize: Int,
                                           pblocksize: Boolean?,
                                           avail_threshold: Int?,
                                           comment: String?,
                                           insecure_tpc: Boolean?,
                                           xen: Boolean?,
                                           rpm: ExtentRpm?,
                                           ro: Boolean?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("iscsi.extent.create", mapOf(
                "name" to name,
                "type" to type,
                "disk" to disk,
                "serial" to serial,
                "path" to path,
                "filesize" to filesize,
                "blocksize" to blocksize,
                "pblocksize" to pblocksize,
                "avail_threshold" to avail_threshold,
                "comment" to comment,
                "insecure_tpc" to insecure_tpc,
                "xen" to xen,
                "rpm" to rpm,
                "ro" to ro
        ))
    }

    override suspend fun deleteIscsiExtent(id: Int, remove: Boolean?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("iscsi.extent.delete", id, remove)
    }

    override suspend fun getIscsiExtent(queryFilters: List<QueryFilter>,
                                        queryOptions: QueryOptions): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("iscsi.extent.query", queryFilters, queryOptions)
    }

    override suspend fun setIscsiExtentDiskChoices(exclude: List<Any>): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("iscsi.extent.disk_choices", exclude)
    }

    override suspend fun updateIscsiExtent(id: Int,
                                           name: String,
                                           type: ExtentType?,
                                           disk: String?,
                                           serial: String?,
                                           path: String?,
                                           filesize: Int?,
                                           blocksize: Int?,
                                           pblocksize: Boolean?,
                                           avail_threshold: Int?,
                                           comment: String?,
                                           insecure_tpc: Boolean?,
                                           xen: Boolean?,
                                           rpm: ExtentRpm?,
                                           ro: Boolean?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("iscsi.extent.update", id, mapOf(
                "name" to name,
                "type" to type,
                "disk" to disk,
                "serial" to serial,
                "path" to path,
                "filesize" to filesize,
                "blocksize" to blocksize,
                "pblocksize" to pblocksize,
                "avail_threshold" to avail_threshold,
                "comment" to comment,
                "insecure_tpc" to insecure_tpc,
                "xen" to xen,
                "rpm" to rpm,
                "ro" to ro
        ))
    }
}