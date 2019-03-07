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

package de.markusressel.freenasrestapiclient.api.v2

import de.markusressel.freenasrestapiclient.api.v2.jails.JailsApi
import de.markusressel.freenasrestapiclient.api.v2.jails.JailsApiManager
import de.markusressel.freenasrestapiclient.api.v2.updates.UpdatesApi
import de.markusressel.freenasrestapiclient.api.v2.updates.UpdatesApiManager
import de.markusressel.freenasrestapiclient.core.BasicAuthConfig

/**
 * Convenience delegation class for easy access to all api methods
 *
 * Created by Markus on 06.02.2018.
 */
class FreeNasRestApiV2Client(baseUrl: String, auth: BasicAuthConfig,
                             val websocketClient: WebsocketApiClient = WebsocketApiClient(baseUrl, auth),
                             jailsApiManager: JailsApiManager = JailsApiManager(websocketClient),
                             updatesApiManager: UpdatesApiManager = UpdatesApiManager(websocketClient)) :
        JailsApi by jailsApiManager,
        UpdatesApi by updatesApiManager {

    /**
     * Connect to the websocket api
     */
    fun connect(listener: WebsocketConnectionListener) {
        websocketClient.setListener(listener)
        websocketClient.connect()
    }

    /**
     * Disconnect the api client
     *
     * @param code the code indicating the reason
     * @param reason a text description of the disconnect reason
     */
    fun disconnect(code: Int = 1000, reason: String = "") {
        websocketClient.disconnect(code, reason)
    }

}