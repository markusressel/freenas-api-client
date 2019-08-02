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

package de.markusressel.freenasrestapiclient.api.v2.iscsi

import de.markusressel.freenasrestapiclient.api.v2.WebsocketApiClient
import de.markusressel.freenasrestapiclient.api.v2.iscsi.auth.IscsiAuthApi
import de.markusressel.freenasrestapiclient.api.v2.iscsi.auth.IscsiAuthApiImpl
import de.markusressel.freenasrestapiclient.api.v2.iscsi.extent.IscsiExtentApi
import de.markusressel.freenasrestapiclient.api.v2.iscsi.extent.IscsiExtentApiImpl
import de.markusressel.freenasrestapiclient.api.v2.iscsi.global.IscsiGlobalApi
import de.markusressel.freenasrestapiclient.api.v2.iscsi.global.IscsiGlobalApiImpl

class IscsiApiImpl(private val websocketApiClient: WebsocketApiClient,
                   iscsiAuthApi: IscsiAuthApi = IscsiAuthApiImpl(websocketApiClient),
                   iscsiExtentApi: IscsiExtentApi = IscsiExtentApiImpl(websocketApiClient),
                   iscsiGlobalApi: IscsiGlobalApi = IscsiGlobalApiImpl(websocketApiClient)
) : IscsiApi, IscsiAuthApi by iscsiAuthApi, IscsiExtentApi by iscsiExtentApi, IscsiGlobalApi by iscsiGlobalApi