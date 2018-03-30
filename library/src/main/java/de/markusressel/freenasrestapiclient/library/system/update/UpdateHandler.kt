package de.markusressel.freenasrestapiclient.library.system.update

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import de.markusressel.freenasrestapiclient.library.RequestManager
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class UpdateHandler(private val requestManager: RequestManager) : UpdateApi {

    override fun getPendingUpdates(limit: Int, offset: Int): Single<List<UpdateModel>> {
        val params = requestManager
                .createLimitOffsetParams(limit, offset)
        return requestManager
                .doRequest("/system/update/check/", params, Method.GET,
                           UpdateModel.ListDeserializer())
    }

    override fun applyPendingUpdates(): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/system/update/update/", Method.POST)
    }

}