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
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum

interface JailApi {

    enum class DsType : ApiEnum {
        ALL,
        JAIL,
        TEMPLATE,
        RELEASE
    }

    /**
     * Activates a pool for iocage usage, and deactivates the rest.
     *
     * @param pool the pool to use

     */
    suspend fun activateJailPool(pool: String? = null): Result<JsonElement, Exception>

    /**
     * Cleans all iocage datasets of [dsType]
     *
     * @param dsType
     */
    suspend fun cleanJail(dsType: DsType? = null): Result<JsonElement, Exception>

    /**
     * Creates a jail
     *
     * @param uuid
     * @param release release to use
     * @param template template to use
     * @param pkglist list of packages
     * @param baseJail base jail to use
     */
    suspend fun createJail(uuid: String? = null, release: String? = null, template: String? = null,
                           pkglist: String? = null, baseJail: Boolean? = null): Result<JsonElement, Exception>

    /**
     * Starts a jail
     *
     * @param title the title of the jail
     */
    suspend fun startJail(title: String): Result<JsonElement, Exception>

    /**
     * Issues a command inside a jail
     *
     * @param jail the title of the jail
     * @param commands list of commands to issue
     * @param hostUser host user to use
     * @param jailUser jail user to use
     */
    suspend fun execJailCommand(jail: String, commands: List<String>, hostUser: String? = null, jailUser: String? = null): Result<JsonElement, Exception>

    /**
     * Exports jail to zip file
     *
     * @param title the title of the jail
     */
    suspend fun exportJail(title: String): Result<JsonElement, Exception>

    /**
     * Fetches a release or plugin
     * TODO: add params
     *
     */
    suspend fun fetchJail(): Result<JsonElement, Exception>

    /**
     * Adds an fstab mount to the jail
     * TODO: add params
     *
     */
    suspend fun addFstabMountToJail(title: String): Result<JsonElement, Exception>

    /**
     * Returns the activated pool if there is one, or None
     *
     */
    suspend fun getActiveJailPool(): Result<JsonElement, Exception>

    // TODO: jail.list_resource
    // TODO: jail.query
    // TODO: jail.rc_action

    /**
     * Stops a jail
     *
     * @param title the title of the jail
     */
    suspend fun stopJail(title: String): Result<JsonElement, Exception>

    /**
     * Updates a jail
     *
     * @param title the title of the jail
     */
    suspend fun updateJail(title: String): Result<JsonElement, Exception>

    // TODO: jail.update_to_latest_patch

    // TODO: jail.upgrade

    /**
     * Deletes a jail
     *
     * @param title the title of the jail
     */
    suspend fun deleteJail(title: String): Result<JsonElement, Exception>

}