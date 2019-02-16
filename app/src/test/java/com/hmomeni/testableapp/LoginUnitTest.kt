package com.hmomeni.testableapp

import androidx.test.core.app.ApplicationProvider
import com.hmomeni.testableapp.entities.User
import com.hmomeni.testableapp.vms.LoginViewModel
import io.reactivex.Completable
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class LoginUnitTest {
    val app: App = ApplicationProvider.getApplicationContext()

    @Mock
    lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel.inject(app)
    }

    @Test
    fun `test LoginViewModel login`() {

    }
}