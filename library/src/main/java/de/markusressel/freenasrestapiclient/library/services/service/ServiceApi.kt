package de.markusressel.freenasrestapiclient.library.services.service

import de.markusressel.freenasrestapiclient.library.RequestManager.Companion.DEFAULT_LIMIT
import de.markusressel.freenasrestapiclient.library.RequestManager.Companion.DEFAULT_OFFSET
import io.reactivex.Single

interface ServiceApi {
    /**
     * Get a list of all services
     */
    fun getServices(limit: Int = DEFAULT_LIMIT,
                    offset: Int = DEFAULT_OFFSET): Single<List<ServiceModel>>


    /**
     * Update a service
     */
    fun updateService(serviceId: Int, srv_service: String,
                      srv_enable: Boolean): Single<ServiceModel>

}