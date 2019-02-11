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

package de.markusressel.freenasrestapiclient.api.v1.storage.dataset

import de.markusressel.freenasrestapiclient.api.v1.RequestManager

/**
 * Created by Markus on 09.02.2018.
 */
class DatasetHandler(private val requestManager: RequestManager) : DatasetApi {

    override fun getDatasets(limit: Int, offset: Int): Single<List<DatasetModel>> {
        val params = requestManager.createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/storage/dataset/", params, Method.GET, DatasetModel.ListDeserializer())
    }

    override fun getDatasets(volumeId: Long, limit: Int, offset: Int): Single<List<DatasetModel>> {
        val params = requestManager.createLimitOffsetParams(limit, offset)
        return requestManager.doRequest("/storage/volume/$volumeId/datasets/", params, Method.GET, DatasetModel.ListDeserializer())
    }

    override fun createDataset(volumeId: Long, data: DatasetModel): Single<DatasetModel> {
        return requestManager.doJsonRequest("/storage/volume/$volumeId/datasets/", Method.POST, data, DatasetModel.SingleDeserializer())
    }

    override fun deleteDataset(volumeId: Long, datasetName: String): Single<Pair<Response, ByteArray>> {
        return requestManager.doRequest("/storage/volume/$volumeId/datasets/$datasetName", Method.DELETE)
    }

}