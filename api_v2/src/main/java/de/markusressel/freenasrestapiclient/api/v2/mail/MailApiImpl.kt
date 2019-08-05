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
import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient
import de.markusressel.freenasrestapiclient.api.v2.mail.MailApi.MailSecurity

class MailApiImpl(val websocketApiClient: WebsocketApiClient) : MailApi {

    override suspend fun getMailConfig(): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("mail.config")
    }

    override suspend fun sendMailConfig(subject: String?,
                                        text: String,
                                        to: List<String>,
                                        cc: List<String>,
                                        interval: Int?,
                                        channel: String?,
                                        timeout: Int?,
                                        attachments: Boolean?,
                                        queue: Boolean?,
                                        extra_headers: Map<String, String>,
                                        pass: String?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("mail.send", mapOf(
                "subject" to subject,
                "text" to text,
                "to" to to,
                "cc" to cc,
                "interval" to interval,
                "channel" to channel,
                "timeout" to timeout,
                "attachments" to attachments,
                "queue" to queue,
                "extra_headers" to extra_headers
        ), mapOf(
                "pass" to pass
        ))
    }

    override suspend fun updateMailConfig(fromemail: String?,
                                          outgoingserver: String?,
                                          port: Int?,
                                          security: MailSecurity?,
                                          smtp: Boolean?,
                                          user: String?,
                                          pass: String?): Result<JsonElement, Exception> {
        return websocketApiClient.callMethod("mail.send", mapOf(
                "fromemail" to fromemail,
                "outgoingserver" to outgoingserver,
                "port" to port,
                "security" to security,
                "smtp" to smtp,
                "user" to user,
                "pass" to pass
        ))
    }

}