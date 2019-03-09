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

import de.markusressel.freenasrestapiclient.api.v2.ApiEnum
import de.markusressel.freenasrestapiclient.api.v2.ApiListener

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
     * @param listener result listener
     */

    fun decryptDisk(devices: List<String>, passphrase: String, listener: ApiListener)

    /**
     * Queries all geli providers.
     * It might be an entire disk or a partition of type freebsd-zfs.
     *
     * @param unused
     * @param listener result listener
     */
    fun getEncryptedDisks(unused: Boolean?, listener: ApiListener)

    /**
     * Helper method to get all disks that are not in use, either by the boot pool or the user pools.
     *
     * @param joinPartitions
     * @param listener result listener
     */
    fun getUnusedDisks(joinPartitions: Boolean = false, listener: ApiListener)

    /**
     * Queries a list of disks.
     *
     * TODO: many parameters
     *
     * @param listener result listener
     */
    fun getDisks(listener: ApiListener)

    /**
     * Update a disk
     *
     * TODO: many parameters
     *
     * @param listener result listener
     */
    fun updateDisk(listener: ApiListener)

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
     * @param listener result listener
     */
    fun wipeDisk(dev: String, type: WipeType, listener: ApiListener)

}