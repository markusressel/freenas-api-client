package de.markusressel.freenasrestapiclient.library.sharing

import de.markusressel.freenasrestapiclient.library.sharing.afp.AfpApi
import de.markusressel.freenasrestapiclient.library.sharing.cifs.CifsApi
import de.markusressel.freenasrestapiclient.library.sharing.nfs.NfsApi

interface SharingApi : AfpApi, CifsApi, NfsApi