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

package de.markusressel.freenasrestapiclient.library.plugins

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson

/**
 * Created by Markus on 06.02.2018.
 */
class PluginModel(
        val id: Long,
        val plugin_api_version: Int,
        val plugin_arch: String,
        val plugin_enabled: Boolean,
        val plugin_ip: String,
        val plugin_jail: String,
        val plugin_name: String,
        val plugin_path: String,
        val plugin_pbiname: String,
        val plugin_port: Int,
        val plugin_version: String
) {

    class Deserializer : ResponseDeserializable<List<PluginModel>> {
        override fun deserialize(content: String): List<PluginModel>? {
            if (content.isEmpty()) {
                return emptyList()
            }

            return Gson().fromJson(content)
        }

    }

}