package de.markusressel.freenasrestapiclient.library.account

import de.markusressel.freenasrestapiclient.library.RequestManager
import de.markusressel.freenasrestapiclient.library.account.group.GroupApi
import de.markusressel.freenasrestapiclient.library.account.group.GroupHandler
import de.markusressel.freenasrestapiclient.library.account.user.UserApi
import de.markusressel.freenasrestapiclient.library.account.user.UserHandler

/**
 * Delegation class for encapsulating api calls into subclasses
 *
 * Created by Markus on 09.02.2018.
 */
class AccountManager(requestManager: RequestManager, userApi: UserApi = UserHandler(requestManager),
                     groupApi: GroupApi = GroupHandler(requestManager)) : UserApi by userApi,
    GroupApi by groupApi, AccountApi