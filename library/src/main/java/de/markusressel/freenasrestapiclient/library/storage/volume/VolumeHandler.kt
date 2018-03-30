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

package de.markusressel.freenasrestapiclient.library.storage.volume

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.jails.jail.VolumeApi
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class VolumeHandler(private val requestManager: RequestManager) : VolumeApi {

    override fun getVolumes(limit: Int, offset: Int): Single<List<VolumeModel>> {
        val params = requestManager
                .createLimitOffsetParams(limit, offset)

        val test: List<Pair<String, String>> = listOf("foo" to "foo", "bar" to "bar")

        return requestManager
                .doRequest("/storage/volume/", params, Method.GET, VolumeModel.ListDeserializer())
    }

    override fun createVolume(
            volumeName: String): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        throw NotImplementedError()
    }

    override fun deleteVolume(
            volumeId: Long): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/storage/volume/$volumeId/start/", Method.DELETE)
    }

}