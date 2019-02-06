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

package de.markusressel.freenasrestapiclient.library.jails.jail

import com.github.kittinunf.fuel.core.Response
import de.markusressel.freenasrestapiclient.library.RequestManager
import io.reactivex.Single

interface JailApi {
    /**
     * Get a list of all jails
     */
    fun getJails(limit: Int = RequestManager.DEFAULT_LIMIT,
                 offset: Int = RequestManager.DEFAULT_OFFSET): Single<List<JailModel>>

    /**
     * Create a jail
     */
    fun createJail(data: JailModel): Single<JailModel>

    /**
     * Start a jail
     */
    fun startJail(jailId: Long): Single<Pair<Response, ByteArray>>

    /**
     * Stop a jail
     */
    fun stopJail(jailId: Long): Single<Pair<Response, ByteArray>>

    /**
     * Restart a jail
     */
    fun restartJail(jailId: Long): Single<Pair<Response, ByteArray>>

    /**
     * Delete a jail
     */
    fun deleteJail(jailId: Long): Single<Pair<Response, ByteArray>>
}