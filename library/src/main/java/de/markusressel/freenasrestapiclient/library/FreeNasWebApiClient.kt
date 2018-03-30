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

package de.markusressel.freenasrestapiclient.library

import de.markusressel.freenasrestapiclient.library.account.AccountApi
import de.markusressel.freenasrestapiclient.library.account.AccountManager
import de.markusressel.freenasrestapiclient.library.jails.JailsApi
import de.markusressel.freenasrestapiclient.library.jails.JailsManager
import de.markusressel.freenasrestapiclient.library.plugins.PluginApi
import de.markusressel.freenasrestapiclient.library.plugins.PluginManager
import de.markusressel.freenasrestapiclient.library.services.ServicesApi
import de.markusressel.freenasrestapiclient.library.services.ServicesManager
import de.markusressel.freenasrestapiclient.library.sharing.SharingApi
import de.markusressel.freenasrestapiclient.library.sharing.SharingManager
import de.markusressel.freenasrestapiclient.library.storage.StorageApi
import de.markusressel.freenasrestapiclient.library.storage.StorageManager
import de.markusressel.freenasrestapiclient.library.system.SystemApi
import de.markusressel.freenasrestapiclient.library.system.SystemManager
import de.markusressel.freenasrestapiclient.library.tasks.TasksApi
import de.markusressel.freenasrestapiclient.library.tasks.TasksManager

/**
 * Convenience delegation class for easy access to all api methods
 *
 * Created by Markus on 06.02.2018.
 */
class FreeNasWebApiClient(private val requestManager: RequestManager = RequestManager(), accountApi: AccountApi = AccountManager(requestManager), jailsApi: JailsApi = JailsManager(requestManager), servicesApi: ServicesApi = ServicesManager(requestManager), sharingApi: SharingApi = SharingManager(requestManager), storageApi: StorageApi = StorageManager(requestManager), systemApi: SystemApi = SystemManager(requestManager), pluginApi: PluginApi = PluginManager(requestManager), tasksApi: TasksApi = TasksManager(requestManager)) : AccountApi by accountApi, JailsApi by jailsApi, ServicesApi by servicesApi, SharingApi by sharingApi, StorageApi by storageApi, SystemApi by systemApi, PluginApi by pluginApi, TasksApi by tasksApi {

    /**
     * Set the hostname for this client
     */
    fun setHostname(hostname: String) {
        requestManager
                .hostname = hostname
    }

    /**
     * Set the api resource for this client (in case it is not the default "/api/")
     */
    fun setApiResource(apiResource: String) {
        requestManager
                .apiResource = apiResource
    }

    /**
     * Set the api version for this client
     */
    fun setapiVersion(apiVersion: String) {
        requestManager
                .apiVersion = apiVersion
    }

    /**
     * Set the BasicAuthConfig for this client
     */
    fun setBasicAuthConfig(basicAuthConfig: BasicAuthConfig) {
        requestManager
                .basicAuthConfig = basicAuthConfig
    }

}