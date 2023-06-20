package com.example.zeebooks.commons.network


import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import retrofit2.http.*


interface ApiService {

    @POST("users/createUser")
    suspend fun createUser(@Body registerRequest: RegisterRequest): User

    @POST("users/login")
    suspend fun login(@Query("email") email: String, @Query("password") password: String): User

    @GET("users/getById/user")
    suspend fun getUserById(@Query("id") userid: String): User

    @GET("users/allUsers")
    suspend fun getAllUsers(): User

    //CATEGORIES
    @GET("category/allCategories")
    suspend fun getCategories(): CategoryModel

}
