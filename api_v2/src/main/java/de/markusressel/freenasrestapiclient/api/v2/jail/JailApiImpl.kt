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
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class JailApiImpl(val websocketApiClient: WebsocketApiClient) : JailApi {
    override suspend fun listJailResource(resourceType: JailApi.ResourceType?, remote: Boolean?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.query")
    }

    override suspend fun getJails(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.query")
    }

    override suspend fun jailRcAction(action: JailApi.Action): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.rc_action", action)
    }

    override suspend fun activateJailPool(pool: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.activate", pool)
    }

    override suspend fun cleanJail(dsType: JailApi.DsType): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.clean", dsType)
    }

    override suspend fun execJailCommand(jail: String, commands: List<String>, hostUser: String?, jailUser: String?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.exec", jail, commands, mapOf(
                "host_user" to hostUser,
                "jail_user" to jailUser
        ))
    }

    override suspend fun exportJail(title: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.export", title)
    }

    override suspend fun fetchJail(): Result<JsonElement, Exception> {
        // TODO: many arguments
        return websocketApiClient.callMethod("jail.fetch")
    }

    override suspend fun addFstabMountToJail(title: String): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getActiveJailPool(): Result<JsonElement, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun createJail(uuid: String, release: String, template: String?,
                                    pkglist: String?, baseJail: Boolean?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.create", mapOf(
                "uuid" to uuid,
                "release" to release,
                "template" to template,
                "pkglist" to pkglist
        ))
    }


    override suspend fun startJail(title: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.start", title)
    }

    override suspend fun stopJail(title: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.stop", title)
    }

    override suspend fun updateJail(title: String, plugin: Boolean): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.update", title, mapOf(
                "plugin" to plugin
        ))
    }

    override suspend fun updateJailToLatestPatch(title: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.update_to_latest_patch", title)
    }

    override suspend fun deleteJail(title: String): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("jail.delete", title)
    }

}