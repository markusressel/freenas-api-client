/*
 * Copyright (c) 2018 Markus Ressel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusressel.freenasrestapiclient.demo

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import de.markusressel.commons.android.material.toast
import de.markusressel.freenasrestapiclient.api.v1.BasicAuthConfig
import de.markusressel.freenasrestapiclient.api.v1.FreeNasWebApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LifecycleActivityBase() {

    private lateinit var freeNasWebApiClient: FreeNasWebApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super
                .onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFreenasRestClient()

        loadUsers()
    }

    private fun setupFreenasRestClient() {
        // create client
        freeNasWebApiClient = FreeNasWebApiClient()

        // set connection properties
        freeNasWebApiClient.setBaseUrl("https://freenas.mydomain.de/api")
        // set basic auth (your freenas root user)
        freeNasWebApiClient.setBasicAuthConfig(BasicAuthConfig(username = "root", password = "password"))
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

                    val usersText = users.joinToString("\n") { toString() }

                    textView.text = usersText
                    toast("Success!")
                }, onError = {
                    textView.text = "Error: ${it.javaClass.simpleName}: ${it.message}"

                    toast("Error", duration = Toast.LENGTH_LONG)
                })
    }
}
