package com.hmomeni.testableapp

import android.app.Application
import com.hmomeni.testableapp.di.ApiModule
import com.hmomeni.testableapp.di.AppModule
import com.hmomeni.testableapp.di.DIComponent
import com.hmomeni.testableapp.di.DaggerDIComponent

class App : Application() {
    lateinit var di: DIComponent
    override fun onCreate() {
        super.onCreate()
        di = DaggerDIComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .build()
    }
}