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

package de.markusressel.freenasrestapiclient.api.v2.disk

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum

interface DiskApi {

    enum class WipeType : ApiEnum {
        /** clean the first few and last megabytes of every partition and disk */
        QUICK,
        /** write whole disk with zero's */
        FULL,
        /** write whole disk with random bytes */
        FULL_RANDOM
    }

    /**
     * Decrypts devices using uploaded encryption key
     *
     * @param devices list of devices
     * @param passphrase encryption key passphrase
     */

    suspend fun decryptDisk(devices: List<String>, passphrase: String): Result<JsonElement, Exception>

    /**
     * Queries all geli providers.
     * It might be an entire disk or a partition of type freebsd-zfs.
     *
     * @param unused
     */
    suspend fun getEncryptedDisks(unused: Boolean?): Result<JsonElement, Exception>

    /**
     * Helper method to get all disks that are not in use, either by the boot pool or the user pools.
     *
     * @param joinPartitions
     */
    suspend fun getUnusedDisks(joinPartitions: Boolean = false): Result<JsonElement, Exception>

    /**
     * Queries a list of disks.
     *
     * TODO: many parameters
     *
     */
    suspend fun getDisks(): Result<JsonElement, Exception>

    /**
     * Update a disk
     *
     * TODO: many parameters
     *
     */
    suspend fun updateDisk(): Result<JsonElement, Exception>

    /**
     * Performs a wipe of a disk dev.
     *
     * It can be of the following modes:
     * - QUICK: clean the first few and last megabytes of every partition and disk
     * - FULL: write whole disk with zero's
     * - FULL_RANDOM: write whole disk with random bytes
     *
     * @param dev device to wipe
     * @param type wipe type
     */
    suspend fun wipeDisk(dev: String, type: WipeType): Result<JsonElement, Exception>

}