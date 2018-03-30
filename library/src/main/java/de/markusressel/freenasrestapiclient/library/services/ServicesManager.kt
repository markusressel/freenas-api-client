package de.markusressel.freenasrestapiclient.library.services

import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.services.service.ServiceApi
import de.markusressel.freenasrestapiclient.library.services.service.ServiceHandler

/**
 * Created by Markus on 09.02.2018.
 */
class ServicesManager(private val requestManager: RequestManager,
                      serviceApi: ServiceApi = ServiceHandler(requestManager)) :
    ServiceApi by serviceApi, ServicesApi