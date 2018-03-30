package de.markusressel.freenasrestapiclient.library.storage.disk

import de.markusressel.freenasrestapiclient.library.RequestManager
import io.reactivex.Single

interface DiskApi {
    /**
     * Get a list of all disks
     */
    fun getDisks(limit: Int = RequestManager.DEFAULT_LIMIT,
                 offset: Int = RequestManager.DEFAULT_OFFSET): Single<List<DiskModel>>

    /**
     * Update a disk
     */
    fun updateDisk(data: DiskModel): Single<DiskModel>
}