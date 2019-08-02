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

package de.markusressel.freenasrestapiclient.api.v2.group

import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject
import com.github.salomonbrys.kotson.toJsonArray
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient

class GroupApiImpl(val websocketApiClient: WebsocketApiClient) : GroupApi {
    override suspend fun createGroup(gid: Int,
                                     name: String,
                                     sudo: Boolean,
                                     allowDuplicateGid: Boolean,
                                     users: List<Int>): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("group.create", jsonObject(
                "gid" to gid,
                "name" to name,
                "sudo" to sudo,
                "allow_duplicate_gid" to allowDuplicateGid,
                "users" to users.toJsonArray()
        ))
    }

    override suspend fun deleteGroup(id: Int, deleteUsers: Boolean): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("group.delete", id, jsonObject(
                "delete_users" to deleteUsers
        ))
    }

    override suspend fun getNextFreeGroupId(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("group.get_next_gid")
    }

    override suspend fun getGroups(queryFilters: List<QueryFilter>,
                                   queryOptions: QueryOptions): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("group.query", queryFilters, queryOptions)
    }

    override suspend fun updateGroup(id: Int,
                                     gid: Int?,
                                     name: String?,
                                     sudo: Boolean?,
                                     allowDuplicateGid: Boolean?,
                                     users: List<Int>?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("group.update", id, jsonObject(
                "gid" to gid,
                "name" to name,
                "sudo" to sudo,
                "allow_duplicate_gid" to allowDuplicateGid,
                "users" to users?.toJsonArray()
        ))
    }
}