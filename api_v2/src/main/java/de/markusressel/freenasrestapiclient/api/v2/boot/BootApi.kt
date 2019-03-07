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

import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface BootApi {

    /**
     * Attach a disk to the boot pool, turning a stripe into a mirror.
     *
     * @param dev the device to attach
     * @param expand determines whether the new disk partition will be the maximum available or the same size as the current disk.
     * @param listener result listener
     */
    fun attachBootPool(dev: String, expand: Boolean, listener: ApiListener)

    /**
     * Detach given dev from boot pool.
     *
     * @param dev the device to detach
     * @param listener result listener
     */
    fun detachBootPool(dev: String, listener: ApiListener)

    /**
     * Returns disks of the boot pool.
     *
     * @param listener result listener
     */
    fun getBootDisks(listener: ApiListener)

    /**
     * Returns the current state of the boot pool, including all vdevs, properties and datasets.
     *
     * @param listener result listener
     */
    fun getBootState(listener: ApiListener)

    /**
     * Replace device [label] on boot pool with [dev].
     *
     * @param label label
     * @param dev device
     * @param listener result listener
     */
    fun replaceBootLabel(label: String, dev: String, listener: ApiListener)

    /**
     * Scrub on boot pool.
     */
    fun scrubBoot(listener: ApiListener)

}