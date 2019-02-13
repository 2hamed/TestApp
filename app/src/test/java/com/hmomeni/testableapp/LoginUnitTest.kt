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
        `when`(viewModel.login("hamed.ma7@gmail.com", "123456"))
            .thenReturn(Completable.complete())

        viewModel.login("hamed.ma7@gmail.com", "123456").test().assertComplete()

        `when`(viewModel.login("hamed.ma@gmail.com", "123456"))
            .thenReturn(
                Completable.error(
                    HttpException(
                        Response.error<User>(
                            401,
                            ResponseBody.create(MediaType.parse("application/text"), "")
                        )
                    )
                )
            )
        viewModel.login("hamed.ma7@gmail.com", "123456").test().assertError(HttpException::class.java)


    }
}