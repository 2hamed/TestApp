package com.hmomeni.testableapp

import androidx.lifecycle.ViewModelProviders
import androidx.test.core.app.ApplicationProvider
import com.hmomeni.testableapp.di.DaggerTestDIComponent
import com.hmomeni.testableapp.di.TestDIComponent
import com.hmomeni.testableapp.vms.LoginViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import retrofit2.HttpException

@RunWith(RobolectricTestRunner::class)
class LoginUnitTest {

    lateinit var viewModel: LoginViewModel
    private lateinit var component: TestDIComponent

    @Before
    fun setup() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
        component = DaggerTestDIComponent.builder()
            .applicationContext(ApplicationProvider.getApplicationContext())
            .build()
        viewModel = ViewModelProviders.of(activity.get(), component.loginViewModelFactory())[LoginViewModel::class.java]
    }

    @Test
    fun `test LoginViewModel isEmailValid valid`() {
        assertTrue(viewModel.isEmailValid("hamed.ma7@gmail.com"))
    }

    @Test
    fun `test LoginViewModel isEmailValid invalid url`() {
        assertFalse(viewModel.isEmailValid("www.example.org"))
    }

    @Test
    fun `test LoginViewModel isEmailValid invalid without @`() {
        assertFalse(viewModel.isEmailValid("hamed.ma7gmail.com"))
    }

    @Test
    fun `test LoginViewModel login correct`() {
        viewModel.login("hamed.ma7@gmail.com", "123456").test().assertComplete()
    }

    @Test
    fun `test LoginViewModel login wrong`() {
        viewModel.login("hamed.ma7@gmail.com", "12456").test().assertError(HttpException::class.java)
    }
}