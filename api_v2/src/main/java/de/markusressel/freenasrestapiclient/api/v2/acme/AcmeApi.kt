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

package de.markusressel.freenasrestapiclient.api.v2.acme

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions

interface AcmeApi {

    /**
     * Get the schemas for all DNS providers we support for
     * ACME DNS Challenge and the respective attributes required
     * for connecting to them while validating a DNS Challenge
     */
    suspend fun getAuthenticatorSchemas(): Result<JsonElement, Exception>

    /**
     * Create a DNS Authenticator
     *
     * Create a specific DNS Authenticator containing required
     * authentication details for the said provider to successfully
     * connect with it
     */
    suspend fun createAcmeConfig(authenticator: String, name: String, attributes: JsonObject): Result<JsonElement, Exception>

    /**
     * Delete DNS Authenticator
     *
     * @param id authenticator id
     */
    suspend fun deleteAcmeConfig(id: Int): Result<JsonElement, Exception>

    /**
     * Query ACME DNS Authenticators
     */
    suspend fun getAcmeDnsAuthenticators(queryFilters: List<QueryFilter> = emptyList(),
                                         queryOptions: QueryOptions = QueryOptions()): Result<JsonElement, Exception>

    /**
     * Update DNS Authenticator
     *
     * @param id authenticator id
     */
    suspend fun updateAcmeDnsAuthenticators(id: Int,
                                            name: String? = null,
                                            attributes: JsonObject? = null): Result<JsonElement, Exception>

}