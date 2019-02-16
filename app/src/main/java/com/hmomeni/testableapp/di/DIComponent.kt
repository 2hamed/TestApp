package com.hmomeni.testableapp.di

import android.content.Context
import com.hmomeni.testableapp.vms.ListViewModel
import com.hmomeni.testableapp.vms.LoginViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, AppModule::class])
interface DIComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun appModule(appModule: AppModule): Builder
        fun apiModule(apiModule: ApiModule): Builder

        fun build(): DIComponent
    }

    fun loginViewModelFactory(): ViewModelFactory<LoginViewModel>
    fun listViewModelFactory(): ViewModelFactory<ListViewModel>
}