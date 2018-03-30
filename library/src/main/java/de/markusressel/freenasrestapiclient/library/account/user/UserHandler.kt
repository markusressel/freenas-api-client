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

package de.markusressel.freenasrestapiclient.library.account.user

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import de.markusressel.freenasrestapiclient.library.RequestManager
import io.reactivex.Single

/**
 * Created by Markus on 09.02.2018.
 */
class UserHandler(private val requestManager: RequestManager) : UserApi {

    override fun getUsers(limit: Int, offset: Int): Single<List<UserModel>> {
        val params = requestManager
                .createLimitOffsetParams(limit, offset)
        return requestManager
                .doRequest("/account/users/", params, Method.GET, UserModel.ListDeserializer())
    }

    override fun createUser(data: UserModel): Single<UserModel> {
        return requestManager
                .doJsonRequest("/account/users/", Method.POST, data, UserModel.SingleDeserializer())
    }

    override fun updateUser(data: UserModel): Single<UserModel> {
        return requestManager
                .doJsonRequest("/account/users/${data.id}/", Method.PUT, data,
                        UserModel.SingleDeserializer())
    }

    override fun deleteUser(user: UserModel): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/account/users/${user.id}/", Method.DELETE)
    }

    override fun setUserPassword(userId: Long,
                                 newPassword: String): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        val passwordMap = jsonObject("bsdusr_password" to newPassword)
        return requestManager
                .doJsonRequest("/account/users/$userId/password/", Method.POST, passwordMap)
    }

    override fun getGroups(userId: Long): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        return requestManager
                .doRequest("/account/users/$userId/groups/", Method.GET)
    }

    override fun setGroups(userId: Long,
                           vararg group: String): Single<Pair<Response, Result<ByteArray, FuelError>>> {
        val data = jsonArray(group.asList())
        return requestManager
                .doJsonRequest("/account/users/$userId/groups/", Method.GET, data)
    }

}