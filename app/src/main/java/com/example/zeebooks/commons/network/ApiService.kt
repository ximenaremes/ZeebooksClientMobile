package com.example.zeebooks.commons.network


import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.LoginRequest
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.domain.model.response.LoginResponse
import com.example.zeebooks.commons.domain.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @POST("users/createUser")
        suspend fun createUser(@Body registerRequest : RegisterRequest): RegisterResponse
    @POST("users/loginUser")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("users/getById/user")
    suspend fun getUserById(@Query("id") userid:String): User
}
