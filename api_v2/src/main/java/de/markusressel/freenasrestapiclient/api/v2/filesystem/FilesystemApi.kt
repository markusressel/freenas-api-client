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

package de.markusressel.freenasrestapiclient.api.v2.filesystem

import com.github.kittinunf.result.Result
import com.google.gson.JsonElement
import de.markusressel.freenasrestapiclient.api.v2.QueryFilter
import de.markusressel.freenasrestapiclient.api.v2.QueryOptions

interface FilesystemApi {

    /**
     * Job to get contents of path.
     *
     * @param path the path to query
     */
    suspend fun getFilesystemContent(path: String): Result<JsonElement, Exception>

    /**
     * Get the contents of a directory.
     *
     * Each entry of the list consists of:
     * - name(str): name of the file
     * - path(str): absolute path of the entry
     * - realpath(str): absolute real path of the entry (if SYMLINK)
     * - type(str): DIRECTORY | FILESYSTEM | SYMLINK | OTHER
     * - size(int): size of the entry
     * - mode(int): file mode/permission
     * - uid(int): user id of entry owner
     * - gid(int): group id of entry owner
     *
     * TODO: query filter params
     *
     * @param path path to query
     */
    suspend fun listFilesystemDir(path: String,
                                  queryFilters: List<QueryFilter> = emptyList(),
                                  queryOptions: QueryOptions = QueryOptions()): Result<JsonElement, Exception>

    /**
     * Job to put contents to path.
     *
     */
    suspend fun putFilesystemContent(path: String,
                                     append: Boolean? = null,
                                     mode: Int? = null): Result<JsonElement, Exception>

    /**
     * Return the filesystem stat(2) for a given path.
     *
     * @param path
     */
    suspend fun filesystemStat(path: String): Result<JsonElement, Exception>

    /**
     * Return stats from the filesystem of a given path.
     *
     * Raises: CallError(ENOENT) - Path not found
     *
     * @param path
     */
    suspend fun filesystemStatFs(path: String): Result<JsonElement, Exception>

}