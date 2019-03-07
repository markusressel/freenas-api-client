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

package de.markusressel.freenasrestapiclient.api.v2.jails

import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface JailsApi {

    enum class DsType {
        ALL,
        JAIL,
        TEMPLATE,
        RELEASE
    }

    /**
     * Activates a pool for iocage usage, and deactivates the rest.
     *
     * @param pool the pool to use
     * @param listener result listener
     */
    fun activateJailPool(pool: String? = null, listener: ApiListener)

    /**
     * Cleans all iocage datasets of [dsType]
     *
     * @param dsType
     * @param listener result listener
     */
    fun cleanJail(dsType: DsType? = null, listener: ApiListener)

    /**
     * Creates a jail
     *
     * @param uuid
     * @param release release to use
     * @param template template to use
     * @param pkglist list of packages
     * @param baseJail base jail to use
     * @param listener result listener
     */
    fun createJail(uuid: String? = null, release: String? = null, template: String? = null,
                   pkglist: String? = null, baseJail: Boolean? = null, listener: ApiListener)

    /**
     * Starts a jail
     *
     * @param title the title of the jail
     * @param listener result listener
     */
    fun startJail(title: String, listener: ApiListener)

    /**
     * Issues a command inside a jail
     *
     * @param jail the title of the jail
     * @param commands list of commands to issue
     * @param hostUser host user to use
     * @param jailUser jail user to use
     * @param listener result listener
     */
    fun execJailCommand(jail: String, commands: List<String>, hostUser: String? = null, jailUser: String? = null, listener: ApiListener)

    /**
     * Exports jail to zip file
     *
     * @param title the title of the jail
     * @param listener result listener
     */
    fun exportJail(title: String, listener: ApiListener)

    /**
     * Fetches a release or plugin
     * TODO: add params
     *
     * @param listener result listener
     */
    fun fetchJail(listener: ApiListener)

    /**
     * Adds an fstab mount to the jail
     * TODO: add params
     *
     * @param listener result listener
     */
    fun addFstabMountToJail(title: String, listener: ApiListener)

    /**
     * Returns the activated pool if there is one, or None
     *
     * @param listener result listener
     */
    fun getActiveJailPool(listener: ApiListener)

    // TODO: jail.list_resource
    // TODO: jail.query
    // TODO: jail.rc_action

    /**
     * Stops a jail
     *
     * @param title the title of the jail
     * @param listener result listener
     */
    fun stopJail(title: String, listener: ApiListener)

    /**
     * Updates a jail
     *
     * @param title the title of the jail
     * @param listener result listener
     */
    fun updateJail(title: String, listener: ApiListener)

    // TODO: jail.update_to_latest_patch

    // TODO: jail.upgrade

    /**
     * Deletes a jail
     *
     * @param title the title of the jail
     * @param listener result listener
     */
    fun deleteJail(title: String, listener: ApiListener)

}