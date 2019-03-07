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

package de.markusressel.freenasrestapiclient.api.v2.auth

import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface AuthApi {

    /**
     * Verify username and password
     *
     * @param username username
     * @param password password
     */
    fun checkUser(username: String = "root", password: String, listener: ApiListener)

    /**
     * Generate a token to be used for authentication.
     *
     * @param ttl time to live
     * @param listener response listener
     */
    fun generateToken(ttl: Int? = null, listener: ApiListener)

    /**
     * Authenticate session using username and password. Currently only root user is allowed.
     *
     * @param username
     * @param password
     * @param listener result listener
     */
    fun login(username: String = "root", password: String, listener: ApiListener)

    /**
     * Deauthenticates an app and if a token exists, removes that from the session.
     *
     * @param listener response listener
     */
    fun logout(listener: ApiListener)

    /**
     * Authenticate using a given token id.
     *
     * @param token auth token
     * @param listener response listener
     */
    fun authenticate(token: String, listener: ApiListener)

}