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
import com.google.gson.annotations.SerializedName
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions

interface IscsiExtentApi {

    enum class ExtentType : ApiEnum {
        DISK,
        FILE;
    }

    enum class ExtentRpm : ApiEnum {
        UNKNOWN,
        SSD,
        @SerializedName("5400")
        _5400,
        @SerializedName("7200")
        _7200,
        @SerializedName("10000")
        _10000,
        @SerializedName("15000")
        _15000;
    }

    /**
     * @param secret must be between 12 and 16 characters
     */
    suspend fun createIscsiExtent(name: String,
                                  type: ExtentType,
                                  disk: String? = null,
                                  serial: String? = null,
                                  path: String? = null,
                                  filesize: Int? = null,
                                  blocksize: Int = 512,
                                  pblocksize: Boolean? = null,
                                  avail_threshold: Int? = null,
                                  comment: String? = null,
                                  insecure_tpc: Boolean? = null,
                                  xen: Boolean? = null,
                                  rpm: ExtentRpm? = null,
                                  ro: Boolean? = null): Result<JsonElement, Exception>

    suspend fun deleteIscsiExtent(id: Int, remove: Boolean? = null): Result<JsonElement, Exception>

    suspend fun getIscsiExtent(queryFilters: List<QueryFilter> = emptyList(),
                               queryOptions: QueryOptions = QueryOptions()): Result<JsonElement, Exception>

    suspend fun setIscsiExtentDiskChoices(exclude: List<Any>): Result<JsonElement, Exception>

    suspend fun updateIscsiExtent(id: Int,
                                  name: String,
                                  type: ExtentType? = null,
                                  disk: String? = null,
                                  serial: String? = null,
                                  path: String? = null,
                                  filesize: Int? = null,
                                  blocksize: Int? = null,
                                  pblocksize: Boolean? = null,
                                  avail_threshold: Int? = null,
                                  comment: String? = null,
                                  insecure_tpc: Boolean? = null,
                                  xen: Boolean? = null,
                                  rpm: ExtentRpm? = null,
                                  ro: Boolean? = null): Result<JsonElement, Exception>

}