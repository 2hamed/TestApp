package com.hmomeni.testableapp.vms

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hmomeni.testableapp.App
import com.hmomeni.testableapp.api.Api
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.Completable
import javax.inject.Inject

open class LoginViewModel : ViewModel() {
    fun inject(app: App) {
        app.di.inject(this)
    }

    @Inject
    lateinit var api: Api

    fun login(email: String, password: String): Completable = api.login(email, password)
        .doOnSuccess {
            Prefs.putString("user", Gson().toJson(it))
        }
        .ignoreElement()
}