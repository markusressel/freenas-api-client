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

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement

interface AuthApi {

    /**
     * Verify username and password
     *
     * @param username username
     * @param password password
     */
    suspend fun checkUser(username: String = "root", password: String): Result<JsonElement, Exception>

    /**
     * Generate a token to be used for authentication.
     *
     * @param ttl time to live
     */
    suspend fun generateToken(ttl: Int? = null): Result<JsonElement, Exception>

    /**
     * Authenticate session using username and password. Currently only root user is allowed.
     *
     * @param username
     * @param password

     */
    suspend fun login(username: String = "root", password: String): Result<JsonElement, Exception>

    /**
     * Deauthenticates an app and if a token exists, removes that from the session.
     */
    suspend fun logout(): Result<JsonElement, Exception>

    /**
     * Authenticate using a given token id.
     *
     * @param token auth token
     */
    suspend fun authenticate(token: String): Result<JsonElement, Exception>

}