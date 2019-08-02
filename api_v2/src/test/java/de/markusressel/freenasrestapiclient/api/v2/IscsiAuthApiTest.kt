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

import com.github.kittinunf.result.map
import com.github.salomonbrys.kotson.get
import de.markusressel.freenasrestapiclient.api.v2.base.TestBase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class IscsiAuthApiTest : TestBase() {

    @Test
    fun testCreateIscsiAuth() {
        runBlocking {
            val result = underTest.createIscsiAuth(
                    tag = 1000,
                    user = "my_user",
                    secret = "secret_12345678",
                    peeruser = "some_other_user",
                    peersecret = "other_12345678"
            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testUpdateIscsiAuth() {
        runBlocking {
            val id = underTest.getIscsiAuth()
                    .map { it["result"].asJsonArray }
                    .map { it.first() }
                    .map { it["id"] }
                    .map { it.asInt }.get()

            val result = underTest.updateIscsiAuth(
                    id = id,
                    tag = 1000,
                    user = "my_user",
                    secret = "secret_12345678",
                    peeruser = "some_other_user",
                    peersecret = "other_12345678"
            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testGetIscsiAuth() {
        runBlocking {
            val result = underTest.getIscsiAuth()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testDeleteIscsiAuth() {
        runBlocking {
            val id = underTest.getIscsiAuth()
                    .map { it["result"].asJsonArray }
                    .map { it.first() }
                    .map { it["id"] }
                    .map { it.asInt }.get()

            val result = underTest.deleteIscsiAuth(id)
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

}