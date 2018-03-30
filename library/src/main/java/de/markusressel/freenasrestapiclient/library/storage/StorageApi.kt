package de.markusressel.freenasrestapiclient.library.storage

import de.markusressel.freenasrestapiclient.library.jails.jail.VolumeApi
import de.markusressel.freenasrestapiclient.library.storage.dataset.DatasetApi
import de.markusressel.freenasrestapiclient.library.storage.disk.DiskApi
import de.markusressel.freenasrestapiclient.library.storage.scrub.ScrubApi
import de.markusressel.freenasrestapiclient.library.storage.snapshot.SnapshotApi
import de.markusressel.freenasrestapiclient.library.storage.task.TaskApi

/**
 * Created by Markus on 09.02.2018.
 */
interface StorageApi : DatasetApi, DiskApi, ScrubApi, SnapshotApi, TaskApi, VolumeApi