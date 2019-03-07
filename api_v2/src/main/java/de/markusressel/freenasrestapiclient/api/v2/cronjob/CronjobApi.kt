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

package de.markusressel.freenasrestapiclient.api.v2.cronjob

import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface CronjobApi {

    /**
     * Creates a new cronjob
     * TODO: many params
     * @param listener result listener
     */
    fun createCronjob(listener: ApiListener)

    /**
     * Queries a list of cronjobs
     *
     * TODO: many params
     * @param listener result listener
     */
    fun getCronjobs(listener: ApiListener)

    /**
     * Updates a cronjob
     *
     * @param id id of the cronjob
     * @param listener result listener
     */
    fun updateCronjob(id: Int, listener: ApiListener)

    /**
     * Validates a cronjob
     *
     * @param id id of the cronjob
     * @param listener result listener
     */
    fun validateCronjon(id: Int, listener: ApiListener)

    /**
     * Deletes a cronjob
     *
     * @param id id of the cronjob
     * @param listener result listener
     */
    fun deleteCronjob(id: Int, listener: ApiListener)


}