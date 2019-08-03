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
import com.google.gson.annotations.SerializedName
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions

interface DiskApi {

    enum class WipeMode : ApiEnum {
        /** clean the first few and last megabytes of every partition and disk */
        QUICK,
        /** write whole disk with zero's */
        FULL,
        /** write whole disk with random bytes */
        FULL_RANDOM
    }

    enum class AcousticLevel : ApiEnum {
        DISABLED,
        MINIMUM,
        MEDIUM,
        MAXIMUM
    }

    enum class AdvancedPowerManagement : ApiEnum {
        @SerializedName("DISABLED")
        DISABLED,
        @SerializedName("1")
        _1,
        @SerializedName("64")
        _64,
        @SerializedName("127")
        _127,
        @SerializedName("128")
        _128,
        @SerializedName("192")
        _192,
        @SerializedName("254")
        _254;
    }

    enum class HddStandby : ApiEnum {
        @SerializedName("ALWAYS ON")
        ALWAYS_ON,
        @SerializedName("5")
        _5,
        @SerializedName("10")
        _10,
        @SerializedName("20")
        _20,
        @SerializedName("30")
        _30,
        @SerializedName("60")
        _60,
        @SerializedName("120")
        _120,
        @SerializedName("180")
        _180,
        @SerializedName("240")
        _240,
        @SerializedName("300")
        _300,
        @SerializedName("330")
        _330;
    }

    /**
     * Decrypts devices using uploaded encryption key
     *
     * @param devices list of devices
     * @param passphrase encryption key passphrase
     */

    suspend fun decryptDisk(devices: List<String>, passphrase: String? = null): Result<JsonElement, Exception>

    /**
     * Queries all geli providers.
     * It might be an entire disk or a partition of type freebsd-zfs.
     *
     * @param unused
     */
    suspend fun getEncryptedDisks(unused: Boolean? = null): Result<JsonElement, Exception>

    /**
     * Helper method to get all disks that are not in use, either by the boot pool or the user pools.
     *
     * @param joinPartitions
     */
    suspend fun getUnusedDisks(joinPartitions: Boolean = false): Result<JsonElement, Exception>

    /**
     * Queries a list of disks.
     */
    suspend fun getDisks(queryFilters: List<QueryFilter> = emptyList(),
                         queryOptions: QueryOptions = QueryOptions()): Result<JsonElement, Exception>

    /**
     * Update a disk
     *
     * TODO: many parameters
     */
    suspend fun updateDisk(id: String,
                           togglesmart: Boolean? = null,
                           acousticlevel: AcousticLevel? = null,
                           advpowermgmt: AdvancedPowerManagement? = null,
                           description: String? = null,
                           hddstandby: HddStandby? = null,
                           passwd: String? = null,
                           smartoptions: String? = null): Result<JsonElement, Exception>

    /**
     * Performs a wipe of a disk dev.
     *
     * It can be of the following modes:
     * - QUICK: clean the first few and last megabytes of every partition and disk
     * - FULL: write whole disk with zero's
     * - FULL_RANDOM: write whole disk with random bytes
     *
     * @param dev device to wipe
     * @param mode wipe mode
     * @param syncCache ??
     */
    suspend fun wipeDisk(dev: String, mode: WipeMode, syncCache: Boolean? = null): Result<JsonElement, Exception>

}