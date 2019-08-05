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

package de.markusressel.freenasrestapiclient.api.v2.mail

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum

interface MailApi {

    enum class MailSecurity : ApiEnum {
        PLAIN,
        SSL,
        TLS
    }

    /**
     * Get Mail configuration
     */
    suspend fun getMailConfig(): Result<JsonElement, Exception>

    /**
     * Sends mail using configured mail settings.
     *
     * If attachments is true, a list compromised of the following dict is required via HTTP upload:
     * - headers(list) - name(str) - value(str) - params(dict) - content (str)
     * [ { "headers": [ { "name": "Content-Transfer-Encoding", "value": "base64" }, { "name": "Content-Type", "value": "application/octet-stream", "params": { "name": "test.txt" } } ], "content": "dGVzdAo=" } ]
     */
    suspend fun sendMailConfig(subject: String? = null,
                               text: String,
                               to: List<String>,
                               cc: List<String> = emptyList(),
                               interval: Int? = null,
                               channel: String? = null,
                               timeout: Int? = null,
                               attachments: Boolean? = null,
                               queue: Boolean? = null,
                               extra_headers: Map<String, String>,
                               pass: String? = null): Result<JsonElement, Exception>

    /**
     * Update Mail configuration
     */
    suspend fun updateMailConfig(
            fromemail: String? = null,
            outgoingserver: String? = null,
            port: Int? = null,
            security: MailSecurity? = null,
            smtp: Boolean? = null,
            user: String? = null,
            pass: String? = null
    ): Result<JsonElement, Exception>

}