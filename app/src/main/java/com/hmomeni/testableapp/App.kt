package com.hmomeni.testableapp

import android.app.Application
import com.hmomeni.testableapp.di.ApiModule
import com.hmomeni.testableapp.di.AppModule
import com.hmomeni.testableapp.di.DIComponent
import com.hmomeni.testableapp.di.DaggerDIComponent
import com.pixplicity.easyprefs.library.Prefs
import timber.log.Timber

open class App : Application() {
    lateinit var di: DIComponent
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        di = DaggerDIComponent.builder()
            .applicationContext(this)
            .build()

        Prefs.Builder().setContext(this).build()
    }
}