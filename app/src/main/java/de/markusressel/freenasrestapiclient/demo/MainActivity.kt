package de.markusressel.freenasrestapiclient.demo

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import de.markusressel.freenasrestapiclient.library.BasicAuthConfig
import de.markusressel.freenasrestapiclient.library.FreeNasWebApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : LifecycleActivityBase() {

    private lateinit var freeNasWebApiClient: FreeNasWebApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super
                .onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFreenasRestClient()

        loadUsers()
    }

    private fun loadUsers() {
        freeNasWebApiClient
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .bindUntilEvent(this, Lifecycle.Event.ON_STOP)
                .subscribeBy(onSuccess = {
                    // here are your users!
                    val users = it

                    showToastLong("Success!")
                }, onError = {
                    showToastLong("Error")
                })
    }

    private fun setupFreenasRestClient() {
        // create client
        freeNasWebApiClient = FreeNasWebApiClient()

        // set connection properties
        freeNasWebApiClient
                .setHostname("freenas.mydomain.de")
        // set basic auth (your freenas root user)
        freeNasWebApiClient
                .setBasicAuthConfig(BasicAuthConfig(username = "root", password = "password"))

        // OPTIONAL: set a custom api resource root path (if necessary)
        freeNasWebApiClient
                .setApiResource("frittenbudeapi")

    }
}
