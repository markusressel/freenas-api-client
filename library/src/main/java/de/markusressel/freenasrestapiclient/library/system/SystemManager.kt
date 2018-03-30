package de.markusressel.freenasrestapiclient.library.system

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.system.alert.AlertApi
import de.markusressel.freenasrestapiclient.library.system.alert.AlertHandler
import de.markusressel.freenasrestapiclient.library.system.update.UpdateApi
import de.markusressel.freenasrestapiclient.library.system.update.UpdateHandler
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class SystemManager(private val requestManager: RequestManager,
                    alertApi: AlertApi = AlertHandler(requestManager),
                    updateApi: UpdateApi = UpdateHandler(requestManager)) : AlertApi by alertApi,
    UpdateApi by updateApi, SystemApi {

    override fun getSoftwareVersion(): Single<SoftwareVersionModel> {
        return requestManager
                .doRequest("/system/version/", Method.GET,
                           SoftwareVersionModel.SingleDeserializer())
    }

    override fun rebootSystem(): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/system/reboot/", Method.POST)
    }

    override fun shutdownSystem(): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/system/shutdown/", Method.POST)
    }

}