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

package de.markusressel.freenasrestapiclient.api.v2.disk

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class DiskApiImpl(val websocketApiClient: WebsocketApiClient) : DiskApi {
    override suspend fun decryptDisk(devices: List<String>, passphrase: String?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("disk.decrypt", devices, passphrase)
    }

    override suspend fun getEncryptedDisks(unused: Boolean?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("disk.get_encrypted", mapOf(
                "unused" to unused
        ))
    }

    override suspend fun getUnusedDisks(joinPartitions: Boolean): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("disk.get_unused", joinPartitions)
    }

    override suspend fun getDisks(queryFilters: List<QueryFilter>,
                                  queryOptions: QueryOptions): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("disk.query", queryFilters, queryOptions)
    }

    override suspend fun updateDisk(id: String,
                                    togglesmart: Boolean?,
                                    acousticlevel: DiskApi.AcousticLevel?,
                                    advpowermgmt: DiskApi.AdvancedPowerManagement?,
                                    description: String?,
                                    hddstandby: DiskApi.HddStandby?,
                                    passwd: String?,
                                    smartoptions: String?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("disk.update", id, mapOf(
                "togglesmart" to togglesmart,
                "acousticlevel" to acousticlevel,
                "advpowermgmt" to advpowermgmt,
                "description" to description,
                "hddstandby" to hddstandby,
                "passwd" to passwd,
                "smartoptions" to smartoptions
        ))
    }

    override suspend fun wipeDisk(dev: String, mode: DiskApi.WipeMode, syncCache: Boolean?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("disk.wipe", dev, mode, syncCache)
    }
}