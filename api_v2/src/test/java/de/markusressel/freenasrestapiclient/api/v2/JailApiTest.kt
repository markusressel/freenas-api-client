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
import de.markusressel.freenasrestapiclient.api.v2.jail.JailApi.Action
import kotlinx.coroutines.runBlocking
import org.junit.Test

class JailApiTest : TestBase() {

    @Test
    fun testactivateJailPool() {
        runBlocking {
            val result = underTest.activateJailPool("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testcleanJail() {
        runBlocking {
            val result = underTest.cleanJail()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testcreateJail() {
        runBlocking {
            val result = underTest.createJail(
                    uuid = "48c9c0fe-bce7-46a0-aab1-1fd1bbb882bc",
                    release = "test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun teststartJail() {
        runBlocking {
            val result = underTest.startJail("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testexecJailCommand() {
        runBlocking {
            val result = underTest.execJailCommand("test", listOf("echo test"))
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testexportJail() {
        runBlocking {
            val result = underTest.exportJail("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testfetchJail() {
        runBlocking {
            val result = underTest.fetchJail()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testaddFstabMountToJail() {
        runBlocking {
            val result = underTest.addFstabMountToJail("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testgetActiveJailPool() {
        runBlocking {
            val result = underTest.getActiveJailPool()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testlistJailResource() {
        runBlocking {
            val result = underTest.listJailResource()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testGetJails() {
        runBlocking {
            val result = underTest.getJails()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testjailRcAction() {
        runBlocking {
            val result = underTest.jailRcAction(action = Action.STOP)
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun teststopJail() {
        runBlocking {
            val result = underTest.stopJail("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testupdateJail() {
        runBlocking {
            val result = underTest.updateJail("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testdeleteJail() {
        runBlocking {
            val result = underTest.deleteJail("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

}