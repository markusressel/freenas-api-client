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

package de.markusressel.freenasrestapiclient.api.v1.jails

import de.markusressel.freenasrestapiclient.api.v1.RequestManager
import de.markusressel.freenasrestapiclient.api.v1.jails.configuration.ConfigurationApi
import de.markusressel.freenasrestapiclient.api.v1.jails.configuration.ConfigurationHandler
import de.markusressel.freenasrestapiclient.api.v1.jails.jail.JailApi
import de.markusressel.freenasrestapiclient.api.v1.jails.jail.JailHandler
import de.markusressel.freenasrestapiclient.api.v1.jails.mountpoint.MountpointApi
import de.markusressel.freenasrestapiclient.api.v1.jails.mountpoint.MountpointHandler
import de.markusressel.freenasrestapiclient.api.v1.jails.template.TemplateApi
import de.markusressel.freenasrestapiclient.api.v1.jails.template.TemplateHandler

/**
 * Created by Markus on 09.02.2018.
 */
class JailsManager(private val requestManager: RequestManager,
                   configurationApi: ConfigurationApi = ConfigurationHandler(requestManager),
                   jailApi: JailApi = JailHandler(requestManager),
                   mountpointApi: MountpointApi = MountpointHandler(requestManager),
                   templateApi: TemplateApi = TemplateHandler(requestManager)) :
        ConfigurationApi by configurationApi, JailApi by jailApi, MountpointApi by mountpointApi,
        TemplateApi by templateApi, JailsApi