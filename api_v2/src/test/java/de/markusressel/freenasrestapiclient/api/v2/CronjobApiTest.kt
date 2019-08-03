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

import de.markusressel.freenasrestapiclient.api.v2.base.TestBase
import de.markusressel.freenasrestapiclient.api.v2.cronjob.CronjobApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CronjobApiTest : TestBase() {

    @Test
    fun testCreateCronjob() {
        runBlocking {
            val result = underTest.createCronjob(
                    enabled = false,
                    command = "echo test",
                    schedule = CronjobApi.CronjobSchedule(),
                    user = "test"
            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testGetCronjobs() {
        runBlocking {
            val result = underTest.getCronjobs()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testUpdateCronjob() {
        runBlocking {
            val result = underTest.updateCronjob(0,
                    enabled = false,
                    command = "echo test",
                    schedule = CronjobApi.CronjobSchedule(),
                    user = "test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testValidateCronjon() {
        runBlocking {
            val result = underTest.validateCronjob(0)
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testDeleteCronjob() {
        runBlocking {
            val result = underTest.deleteCronjob(0)
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

}