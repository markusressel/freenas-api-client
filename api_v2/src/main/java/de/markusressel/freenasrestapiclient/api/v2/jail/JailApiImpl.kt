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

package de.markusressel.freenasrestapiclient.api.v2.jail

import com.github.salomonbrys.kotson.addPropertyIfNotNull
import com.github.salomonbrys.kotson.jsonObject
import de.markusressel.freenasrestapiclient.api.v2.ApiListener
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class JailApiImpl(val websocketApiClient: WebsocketApiClient) : JailApi {

    override fun activateJailPool(pool: String?, listener: ApiListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cleanJail(dsType: JailApi.DsType?, listener: ApiListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execJailCommand(jail: String, commands: List<String>, hostUser: String?, jailUser: String?, listener: ApiListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exportJail(title: String, listener: ApiListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchJail(listener: ApiListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addFstabMountToJail(title: String, listener: ApiListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getActiveJailPool(listener: ApiListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createJail(uuid: String?, release: String?, template: String?,
                            pkglist: String?, baseJail: Boolean?, listener: ApiListener) {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("uuid", uuid)
            addPropertyIfNotNull("release", release)
            addPropertyIfNotNull("template", template)
            addPropertyIfNotNull("pkglist", pkglist)
        }
        websocketApiClient.callMethod("jail.create", arguments, listener = listener)
    }


    override fun startJail(title: String, listener: ApiListener) {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        websocketApiClient.callMethod("jail.start", arguments, listener = listener)
    }

    override fun stopJail(title: String, listener: ApiListener) {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        websocketApiClient.callMethod("jail.stop", arguments, listener = listener)
    }

    override fun updateJail(title: String, listener: ApiListener) {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        websocketApiClient.callMethod("jail.update", arguments, listener = listener)
    }

    override fun deleteJail(title: String, listener: ApiListener) {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        websocketApiClient.callMethod("jail.delete", arguments, listener = listener)
    }

}