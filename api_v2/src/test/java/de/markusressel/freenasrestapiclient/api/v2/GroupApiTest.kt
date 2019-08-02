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

package de.markusressel.freenasrestapiclient.api.v2

import com.github.salomonbrys.kotson.get
import de.markusressel.freenasrestapiclient.api.v2.base.TestBase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GroupApiTest : TestBase() {

    @Test
    fun testCreateGroup() {
        runBlocking {
            val result = underTest.createGroup(
                    gid = 1005,
                    name = TEST_GROUP_NAME,
                    sudo = false,
                    allowDuplicateGid = true,
                    users = emptyList()
            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testDeleteGroup() {
        runBlocking {
            val groups = underTest.getGroups()
            val group = groups.component1()!!["result"].asJsonArray.first {
                it["group"].asString.startsWith(TEST_GROUP_NAME, ignoreCase = true)
            }
            val group_id = group["id"].asInt

            val result = underTest.deleteGroup(
                    id = group_id,
                    deleteUsers = false
            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testGetNextFreeGroupId() {
        runBlocking {
            val result = underTest.getNextFreeGroupId()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testGetGroups() {
        runBlocking {
            val result = underTest.getGroups()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testUpdateGroup() {
        runBlocking {
            val groups = underTest.getGroups()
            val group = groups.component1()!!["result"].asJsonArray.first {
                it["group"].asString.contains(TEST_GROUP_NAME, ignoreCase = true)
            }
            val group_id = group["id"].asInt

            val result = underTest.updateGroup(
                    id = group_id,
                    gid = 1005,
                    name = "${TEST_GROUP_NAME}2",
                    sudo = false,
                    allowDuplicateGid = true,
                    users = emptyList()
            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    companion object {
        const val TEST_GROUP_NAME = "my-group"
    }

}