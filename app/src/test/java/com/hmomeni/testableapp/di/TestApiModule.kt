package com.hmomeni.testableapp.di

import com.hmomeni.testableapp.api.Api
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class TestApiModule {
    @Singleton
    @Provides
    fun providesApi(): Api {
        return Mockito.mock(Api::class.java)
    }
}