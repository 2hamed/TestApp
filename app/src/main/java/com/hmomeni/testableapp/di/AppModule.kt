package com.hmomeni.testableapp.di

import com.hmomeni.testableapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {
    @Provides
    fun providesApp(): App = app
}