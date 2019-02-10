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

package de.markusressel.freenasrestapiclient.library

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.salomonbrys.kotson.gsonTypeToken
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Extension function to create a single element deserializer for the given type
 */
@JvmSuppressWildcards(suppress = true)
inline fun <reified T : Any> singleDeserializer(): ResponseDeserializable<T> {
    return SingleDeserializer(gsonTypeToken<T>())
}

/**
 * Extension function to create a list deserializer for the given type
 */
@JvmSuppressWildcards(suppress = true)
inline fun <reified T : Any> listDeserializer(): ResponseDeserializable<List<T>> {
    return ListDeserializer(gsonTypeToken<List<T>>())
}

/**
 * Generic single element deserializer
 */
@JvmSuppressWildcards(suppress = true)
class SingleDeserializer<T : Any>(val type: Type) : ResponseDeserializable<T> {
    override fun deserialize(content: String): T? {
        return Gson().fromJson(content, type)
    }
}

/**
 * Generic list deserializer
 */
@JvmSuppressWildcards(suppress = true)
class ListDeserializer<T : Any>(val type: Type) : ResponseDeserializable<List<T>> {
    override fun deserialize(content: String): List<T>? {
        if (content.isEmpty()) {
            return emptyList()
        }

        return Gson().fromJson(content, type)
    }
}