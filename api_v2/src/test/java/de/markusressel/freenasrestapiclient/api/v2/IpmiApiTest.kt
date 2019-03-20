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

class IpmiApiTest : TestBase() {

    @Test
    fun testGetIpmiChannels() {
        runBlocking {
            val result = underTest.getIpmiChannels()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testIdentifyIpmi() {
        runBlocking {
            val result = underTest.identifyIpmi(seconds = 5, force = false)
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testisIpmiLoaded() {
        runBlocking {
            val result = underTest.isIpmiLoaded()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testgetIpmi() {
        runBlocking {
            val result = underTest.getIpmi()
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

    @Test
    fun testupdateIpmi() {
        runBlocking {
            val result = underTest.updateIpmi(
                    channel = 12345,
                    ipaddress = "192.168.0.150",
                    netmask = "255.255.255.0",
                    gateway = "127.0.0.1",
                    password = "test",
                    dhcp = true,
                    vlan = 0
            )
            result.fold(success = {
                println("$it")
            }, failure = {
                throw it
            })
        }
    }

}