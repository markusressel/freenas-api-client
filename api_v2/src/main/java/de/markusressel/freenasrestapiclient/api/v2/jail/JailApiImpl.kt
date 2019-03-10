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

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.addPropertyIfNotNull
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class JailApiImpl(val websocketApiClient: WebsocketApiClient) : JailApi {
    override suspend fun listJailResource(resourceType: JailApi.ResourceType?, remote: Boolean?): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getJails(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.query")
    }

    override suspend fun jailRcAction(): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun activateJailPool(pool: String?): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cleanJail(dsType: JailApi.DsType?): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun execJailCommand(jail: String, commands: List<String>, hostUser: String?, jailUser: String?): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun exportJail(title: String): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchJail(): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addFstabMountToJail(title: String): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getActiveJailPool(): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun createJail(uuid: String?, release: String?, template: String?,
                                    pkglist: String?, baseJail: Boolean?): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("uuid", uuid)
            addPropertyIfNotNull("release", release)
            addPropertyIfNotNull("template", template)
            addPropertyIfNotNull("pkglist", pkglist)
        }
        return websocketApiClient.callMethod("jail.create", arguments)
    }


    override suspend fun startJail(title: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        return websocketApiClient.callMethod("jail.start", arguments)
    }

    override suspend fun stopJail(title: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        return websocketApiClient.callMethod("jail.stop", arguments)
    }

    override suspend fun updateJail(title: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        return websocketApiClient.callMethod("jail.update", arguments)
    }

    override suspend fun deleteJail(title: String): Result<JsonElement, Exception> {
        val arguments = jsonObject().apply {
            addPropertyIfNotNull("title", title)
        }
        return websocketApiClient.callMethod("jail.delete", arguments)
    }

}