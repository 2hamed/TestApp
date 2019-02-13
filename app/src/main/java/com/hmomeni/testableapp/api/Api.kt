package com.hmomeni.testableapp.api

import com.hmomeni.testableapp.entities.User
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email: String, @Field("password") password: String): Single<User>

    @GET("users")
    fun fetchUsers(): Single<List<User>>

}