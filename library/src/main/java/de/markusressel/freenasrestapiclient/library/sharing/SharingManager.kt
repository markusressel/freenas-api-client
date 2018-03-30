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

package de.markusressel.freenasrestapiclient.library.sharing

import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.sharing.afp.AfpApi
import de.markusressel.freenasrestapiclient.library.sharing.afp.AfpShareHandler
import de.markusressel.freenasrestapiclient.library.sharing.cifs.CifsApi
import de.markusressel.freenasrestapiclient.library.sharing.cifs.CifsShareHandler
import de.markusressel.freenasrestapiclient.library.sharing.nfs.NfsApi
import de.markusressel.freenasrestapiclient.library.sharing.nfs.NfsShareHandler

/**
 * Created by Markus on 10.02.2018.
 */
class SharingManager(private val requestManager: RequestManager,
                     afpApi: AfpApi = AfpShareHandler(requestManager),
                     cifsApi: CifsApi = CifsShareHandler(requestManager),
                     nfsApi: NfsApi = NfsShareHandler(requestManager)) : SharingApi,
        AfpApi by afpApi, CifsApi by cifsApi, NfsApi by nfsApi