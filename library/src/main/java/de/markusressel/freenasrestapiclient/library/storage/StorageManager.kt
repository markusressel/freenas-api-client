package de.markusressel.freenasrestapiclient.library.storage

import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.jails.jail.VolumeApi
import de.markusressel.freenasrestapiclient.library.storage.dataset.DatasetApi
import de.markusressel.freenasrestapiclient.library.storage.dataset.DatasetHandler
import de.markusressel.freenasrestapiclient.library.storage.disk.DiskApi
import de.markusressel.freenasrestapiclient.library.storage.disk.DiskHandler
import de.markusressel.freenasrestapiclient.library.storage.scrub.ScrubApi
import de.markusressel.freenasrestapiclient.library.storage.scrub.ScrubHandler
import de.markusressel.freenasrestapiclient.library.storage.snapshot.SnapshotApi
import de.markusressel.freenasrestapiclient.library.storage.snapshot.SnapshotHandler
import de.markusressel.freenasrestapiclient.library.storage.task.TaskApi
import de.markusressel.freenasrestapiclient.library.storage.task.TaskHandler
import de.markusressel.freenasrestapiclient.library.storage.volume.VolumeHandler

/**
 * Created by Markus on 09.02.2018.
 */
class StorageManager(private val requestManager: RequestManager,
                     datasetApi: DatasetApi = DatasetHandler(requestManager),
                     diskApi: DiskApi = DiskHandler(requestManager),
                     scrubApi: ScrubApi = ScrubHandler(requestManager),
                     snapshotApi: SnapshotApi = SnapshotHandler(requestManager),
                     taskApi: TaskApi = TaskHandler(requestManager),
                     volumeApi: VolumeApi = VolumeHandler(requestManager)) :
    DatasetApi by datasetApi, DiskApi by diskApi, ScrubApi by scrubApi, SnapshotApi by snapshotApi,
    TaskApi by taskApi, VolumeApi by volumeApi, StorageApi