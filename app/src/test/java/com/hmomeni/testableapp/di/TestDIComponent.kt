package com.hmomeni.testableapp.di

import dagger.Component

@Component(modules = [TestApiModule::class])
interface TestDIComponent {
}