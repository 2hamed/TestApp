package com.hmomeni.testableapp.vms

import androidx.lifecycle.ViewModel
import com.hmomeni.canto.api.Api
import com.hmomeni.testableapp.App
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    fun inject(app: App) {
        app.di.inject(this)
    }

    @Inject
    lateinit var api: Api
}