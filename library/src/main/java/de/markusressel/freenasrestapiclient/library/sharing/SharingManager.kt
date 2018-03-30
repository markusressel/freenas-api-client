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