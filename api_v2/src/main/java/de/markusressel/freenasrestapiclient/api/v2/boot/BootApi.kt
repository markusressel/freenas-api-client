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

package de.markusressel.freenasrestapiclient.api.v2.boot

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement

interface BootApi {

    /**
     * Attach a disk to the boot pool, turning a stripe into a mirror.
     *
     * @param dev the device to attach
     * @param expand determines whether the new disk partition will be the maximum available or
     *               the same size as the current disk.
     */
    suspend fun attachBootPool(dev: String, expand: Boolean): Result<JsonElement, Exception>

    /**
     * Detach given dev from boot pool.
     *
     * @param dev the device to detach
     */
    suspend fun detachBootPool(dev: String): Result<JsonElement, Exception>

    /**
     * Returns disks of the boot pool.
     *
     */
    suspend fun getBootDisks(): Result<JsonElement, Exception>

    /**
     * Returns the current state of the boot pool, including all vdevs, properties and datasets.
     *
     */
    suspend fun getBootState(): Result<JsonElement, Exception>

    /**
     * Replace device [label] on boot pool with [dev].
     *
     * @param label label
     * @param dev device
     */
    suspend fun replaceBootLabel(label: String, dev: String): Result<JsonElement, Exception>

    /**
     * Scrub on boot pool.
     */
    suspend fun scrubBoot(): Result<JsonElement, Exception>

}