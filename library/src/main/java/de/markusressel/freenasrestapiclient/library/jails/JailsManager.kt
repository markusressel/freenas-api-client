package de.markusressel.freenasrestapiclient.library.jails

import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.jails.configuration.ConfigurationApi
import de.markusressel.freenasrestapiclient.library.jails.configuration.ConfigurationHandler
import de.markusressel.freenasrestapiclient.library.jails.jail.JailApi
import de.markusressel.freenasrestapiclient.library.jails.jail.JailHandler
import de.markusressel.freenasrestapiclient.library.jails.mountpoint.MountpointApi
import de.markusressel.freenasrestapiclient.library.jails.mountpoint.MountpointHandler
import de.markusressel.freenasrestapiclient.library.jails.template.TemplateApi
import de.markusressel.freenasrestapiclient.library.jails.template.TemplateHandler

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