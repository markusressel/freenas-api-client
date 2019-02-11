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

package de.markusressel.freenasrestapiclient.api.v1.account.user

import com.github.kittinunf.fuel.core.Response
import de.markusressel.freenasrestapiclient.core.RequestManager
import io.reactivex.Single

interface UserApi {

    /**
     * Get a list of all users
     */
    fun getUsers(limit: Int = RequestManager.DEFAULT_LIMIT,
                 offset: Int = RequestManager.DEFAULT_OFFSET): Single<List<UserModel>>

    /**
     * Create a new user
     */
    fun createUser(data: UserModel): Single<UserModel>

    /**
     * Update an existing user
     */
    fun updateUser(data: UserModel): Single<UserModel>

    /**
     * Delete a user
     */
    fun deleteUser(user: UserModel): Single<Pair<Response, ByteArray>>

    /**
     * Set a password for a user
     */
    fun setUserPassword(userId: Long,
                        newPassword: String): Single<Pair<Response, ByteArray>>

    /**
     * Get user auxiliary groups
     */
    fun getGroups(userId: Long): Single<Pair<Response, ByteArray>>

    /**
     * Get user auxiliary groups
     */
    fun setGroups(userId: Long,
                  vararg group: String): Single<Pair<Response, ByteArray>>
}