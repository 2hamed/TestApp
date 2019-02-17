package com.hmomeni.testableapp.di

import com.hmomeni.testableapp.TestApp
import dagger.Module
import dagger.Provides

@Module
class TestAppModule(val app: TestApp) {
    @Provides
    fun providesTestApp() = app
}