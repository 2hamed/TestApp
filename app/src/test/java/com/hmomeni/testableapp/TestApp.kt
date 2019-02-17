package com.hmomeni.testableapp

import android.app.Application
import com.hmomeni.testableapp.di.DaggerTestDIComponent
import com.hmomeni.testableapp.di.TestDIComponent
import com.pixplicity.easyprefs.library.Prefs

class TestApp : Application() {

    lateinit var di: TestDIComponent
    override fun onCreate() {
        super.onCreate()
        di = DaggerTestDIComponent.builder()
            .applicationContext(this)
            .build()

        Prefs.Builder().setContext(this).build()
    }
}