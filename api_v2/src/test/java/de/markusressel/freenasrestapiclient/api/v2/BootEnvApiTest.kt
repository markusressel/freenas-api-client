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
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BootEnvApiTest : TestBase() {

    @Test
    fun testGetBootEnvs() {
        runBlocking {
            val result = underTest.getBootEnvs()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testActivateBootEnv() {
        runBlocking {
            val result = underTest.activateBootEnv("default")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testCreateBootEnv() {
        runBlocking {
            val result = underTest.createBootEnv("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testSetBootEnvAttribute() {
        runBlocking {
            val result = underTest.setBootEnvAttribute("default", true)
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testUpdateBootEnv() {
        runBlocking {
            val result = underTest.updateBootEnv("test", "test2")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
            val result2 = underTest.updateBootEnv("test2", "test")
            result2.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testDeleteBootEnv() {
        runBlocking {
            val result = underTest.deleteBootEnv("test")
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

}