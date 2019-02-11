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

package de.markusressel.freenasrestapiclient.api.v1

import de.markusressel.freenasrestapiclient.api.v1.account.AccountApi
import de.markusressel.freenasrestapiclient.api.v1.account.AccountManager
import de.markusressel.freenasrestapiclient.api.v1.jails.JailsApi
import de.markusressel.freenasrestapiclient.api.v1.jails.JailsManager
import de.markusressel.freenasrestapiclient.api.v1.plugins.PluginApi
import de.markusressel.freenasrestapiclient.api.v1.plugins.PluginManager
import de.markusressel.freenasrestapiclient.api.v1.services.ServicesApi
import de.markusressel.freenasrestapiclient.api.v1.services.ServicesManager
import de.markusressel.freenasrestapiclient.api.v1.sharing.SharingApi
import de.markusressel.freenasrestapiclient.api.v1.sharing.SharingManager
import de.markusressel.freenasrestapiclient.api.v1.storage.StorageApi
import de.markusressel.freenasrestapiclient.api.v1.storage.StorageManager
import de.markusressel.freenasrestapiclient.api.v1.system.SystemApi
import de.markusressel.freenasrestapiclient.api.v1.system.SystemManager
import de.markusressel.freenasrestapiclient.api.v1.tasks.TasksApi
import de.markusressel.freenasrestapiclient.api.v1.tasks.TasksManager

/**
 * Convenience delegation class for easy access to all api methods
 *
 * Created by Markus on 06.02.2018.
 */
class FreeNasWebApiClient(private val requestManager: RequestManager = RequestManager(),
                          accountApi: AccountApi = AccountManager(requestManager),
                          jailsApi: JailsApi = JailsManager(requestManager),
                          servicesApi: ServicesApi = ServicesManager(requestManager),
                          sharingApi: SharingApi = SharingManager(requestManager),
                          storageApi: StorageApi = StorageManager(requestManager),
                          systemApi: SystemApi = SystemManager(requestManager),
                          pluginApi: PluginApi = PluginManager(requestManager),
                          tasksApi: TasksApi = TasksManager(requestManager)) :
        AccountApi by accountApi,
        JailsApi by jailsApi,
        ServicesApi by servicesApi,
        SharingApi by sharingApi,
        StorageApi by storageApi,
        SystemApi by systemApi,
        PluginApi by pluginApi,
        TasksApi by tasksApi {

    /**
     * Set the baseUrl for this client
     */
    fun setBaseUrl(url: String) {
        requestManager.baseUrl = url
    }

    /**
     * Set the api version for this client
     */
    fun setapiVersion(apiVersion: String) {
        requestManager.apiVersion = apiVersion
    }

    /**
     * Set the BasicAuthConfig for this client
     */
    fun setBasicAuthConfig(basicAuthConfig: BasicAuthConfig) {
        requestManager.basicAuthConfig = basicAuthConfig
    }

}