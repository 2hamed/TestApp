package com.hmomeni.testableapp.di

import com.hmomeni.testableapp.api.Api
import com.hmomeni.testableapp.entities.User
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.mockito.Mockito.*
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Singleton

@Module
class TestApiModule {

    private val USERS = listOf(
        User(1, "hamed.ma7@gmail.com", "Hamed Momeni", null, "212435466432"),
        User(2, "amir@gmail.com", "Amir Googool", null, "21243sddf6432")
    )

    @Provides
    @Singleton
    fun providesApi(): Api {
        val api = mock(Api::class.java)
        `when`(api.fetchUsers()).thenReturn(Single.just(USERS))
        `when`(api.login(anyString(), anyString())).thenReturn(
            Single.error(
                HttpException(
                    Response.error<User>(
                        400, ResponseBody.create(
                            MediaType.parse("text/plain"), "Wrong email and password"
                        )
                    )
                )
            )
        )
        `when`(api.login("hamed.ma7@gmail.com", "123456")).thenReturn(Single.just(USERS[0]))
        return api
    }
}