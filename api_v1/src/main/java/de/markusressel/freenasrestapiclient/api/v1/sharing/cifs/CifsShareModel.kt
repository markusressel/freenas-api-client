/*
 * Copyright (c) 2018 Markus Ressel
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

package de.markusressel.freenasrestapiclient.api.v1.sharing.cifs

/**
 * Created by Markus on 06.02.2018.
 */
class CifsShareModel(val cifs_hostsallow: String, val cifs_name: String, val cifs_home: Boolean,
                     val cifs_default_permissions: Boolean, val cifs_guestok: Boolean,
                     val cifs_showhiddenfiles: Boolean, val cifs_hostsdeny: String,
                     val cifs_recyclebin: Boolean, val cifs_auxsmbconf: String,
                     val cifs_comment: String, val cifs_path: String, val cifs_ro: Boolean,
                     val cifs_guestonly: Boolean, val id: Long, val cifs_browsable: Boolean) {

    class SingleDeserializer : ResponseDeserializable<CifsShareModel> {
        override fun deserialize(content: String): CifsShareModel? {
            return Gson()
                    .fromJson(content)
        }
    }

    class ListDeserializer : ResponseDeserializable<List<CifsShareModel>> {
        override fun deserialize(content: String): List<CifsShareModel>? {
            if (content.isEmpty()) {
                return emptyList()
            }

            return Gson()
                    .fromJson(content)
        }

    }

}