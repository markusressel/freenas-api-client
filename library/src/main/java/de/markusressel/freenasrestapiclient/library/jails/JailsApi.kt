package de.markusressel.freenasrestapiclient.library.jails

import de.markusressel.freenasrestapiclient.library.jails.configuration.ConfigurationApi
import de.markusressel.freenasrestapiclient.library.jails.jail.JailApi
import de.markusressel.freenasrestapiclient.library.jails.mountpoint.MountpointApi
import de.markusressel.freenasrestapiclient.library.jails.template.TemplateApi

/**
 * Created by Markus on 09.02.2018.
 */
interface JailsApi : ConfigurationApi, JailApi, MountpointApi, TemplateApi