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

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement

interface CronjobApi {

    /**
     * Creates a new cronjob
     * TODO: many params
     */
    suspend fun createCronjob(): Result<JsonElement, Exception>

    /**
     * Queries a list of cronjobs
     *
     * TODO: many params
     */
    suspend fun getCronjobs(): Result<JsonElement, Exception>

    /**
     * Updates a cronjob
     *
     * @param id id of the cronjob
     */
    suspend fun updateCronjob(id: Int): Result<JsonElement, Exception>

    /**
     * Validates a cronjob
     *
     * @param id id of the cronjob
     */
    suspend fun validateCronjon(id: Int): Result<JsonElement, Exception>

    /**
     * Deletes a cronjob
     *
     * @param id id of the cronjob
     */
    suspend fun deleteCronjob(id: Int): Result<JsonElement, Exception>


}