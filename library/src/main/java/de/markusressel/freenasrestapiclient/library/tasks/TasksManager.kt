package de.markusressel.freenasrestapiclient.library.tasks

import de.markusressel.freenasrestapiclient.library.tasks.TasksApi
import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.tasks.smart.SMARTTaskApi
import de.markusressel.freenasrestapiclient.library.tasks.smart.SMARTTaskHandler

/**
 * Created by Markus on 09.02.2018.
 */
class TasksManager(private val requestManager: RequestManager,
                   smartTaskApi: SMARTTaskApi = SMARTTaskHandler(requestManager)) : TasksApi,
    SMARTTaskApi by smartTaskApi