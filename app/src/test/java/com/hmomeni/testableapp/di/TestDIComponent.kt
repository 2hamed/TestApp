package com.hmomeni.testableapp.di

import android.content.Context
import com.hmomeni.testableapp.vms.ListViewModel
import com.hmomeni.testableapp.vms.LoginViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestApiModule::class])
interface TestDIComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun apiModule(apiModule: TestApiModule): Builder

        fun build(): TestDIComponent
    }

    fun loginViewModelFactory(): ViewModelFactory<LoginViewModel>
    fun listViewModelFactory(): ViewModelFactory<ListViewModel>
}