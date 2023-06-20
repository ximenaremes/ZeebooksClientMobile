package com.example.zeebooks.commons.network


import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.domain.model.response.RegisterResponse
import retrofit2.http.*


interface ApiService {

    @POST("users/createUser")
    suspend fun createUser(@Body registerRequest: RegisterRequest): User

    @POST("users/login")
    suspend fun login(@Query("email")email: String, @Query("password")password: String): User


    @GET("users/getById/user")
    suspend fun getUserById(@Query("id") userid: String): User
}
