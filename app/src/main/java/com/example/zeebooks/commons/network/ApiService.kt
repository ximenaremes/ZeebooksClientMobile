package com.example.zeebooks.commons.network


import com.example.zeebooks.feature_onboarding.domain.model.User
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @POST("users/createUser")
        suspend fun createUser(@Body user: User): User

//   POST https://zeebooks.herokuapp.com/users/createUser

    @GET("users/getById/user")
    suspend fun getUserById(@Query("id") userid:String): User
}
