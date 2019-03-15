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

package de.markusressel.freenasrestapiclient.api.v2.certificate

import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.ApiEnum

interface CertificateApi : CertificateAuthorityApi {

    enum class CreateType(private val jsonValue: String) : ApiEnum {
        INTERNAL("CERTIFICATE_CREATE_INTERNAL"),
        IMPORTED("CERTIFICATE_CREATE_IMPORTED"),
        STANDARD("CERTIFICATE_CREATE"),
        CSR("CERTIFICATE_CREATE_CSR");

        override fun toJsonValue(): String = jsonValue
    }

    enum class DigestAlgorithm : ApiEnum {
        SHA1,
        SHA224,
        SHA256,
        SHA384,
        SHA512;
    }

    /**
     * Creates a certificate
     *
     * TODO: params
     */
    suspend fun createCertificate(signedBy: Int,
                                  keyLength: Int,
                                  type: Int,
                                  lifetime: Int,
                                  serial: Int,
                                  certificate: String,
                                  city: String,
                                  common: String,
                                  country: String,
                                  CSR: String,
                                  email: String,
                                  name: String,
                                  organization: String,
                                  passphrase: String,
                                  privatekey: String,
                                  state: String,
                                  createType: CreateType,
                                  digestAlgorithm: DigestAlgorithm,
                                  san: List<String>)

    /**
     * Queries a list of certificates
     *
     * TODO: query params
     */
    suspend fun getCertificates(): List<JsonElement>

    /**
     * Updates a certificate
     *
     * @param id id of the certificate
     * @param name new name
     * @param certificate
     */
    suspend fun updateCertificate(id: Int, name: String, certificate: String)

    /**
     * Deletes a certificate
     *
     * @param id id of the certificate
     */
    suspend fun deleteCertificate(id: Int)

}