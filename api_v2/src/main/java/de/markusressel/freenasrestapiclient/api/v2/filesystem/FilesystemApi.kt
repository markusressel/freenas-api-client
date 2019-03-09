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

import de.markusressel.freenasrestapiclient.api.v2.ApiListener

interface FilesystemApi {

    /**
     * Job to get contents of path.
     *
     * @param path the path to query
     * @param listener result listener
     */
    fun getFilesystemContent(path: String, listener: ApiListener)

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
     * @param listener result listener
     */
    fun listFilesystemDir(path: String, listener: ApiListener)

    /**
     * Job to put contents to path.
     *
     * @param listener result listener
     */
    fun putFilesystemContent(path: String, listener: ApiListener)

    /**
     * Return the filesystem stat(2) for a given path.
     *
     * @param path
     * @param listener result listener
     */
    fun filesystemStat(path: String, listener: ApiListener)

    /**
     * Return stats from the filesystem of a given path.
     *
     * Raises: CallError(ENOENT) - Path not found
     *
     * @param path
     * @param listener result listener
     */
    fun filesystemStatFs(path: String, listener: ApiListener)

}