package com.hmomeni.testableapp.di

import com.hmomeni.testableapp.vms.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, AppModule::class])
interface DIComponent {
    fun inject(loginViewModel: LoginViewModel)
}