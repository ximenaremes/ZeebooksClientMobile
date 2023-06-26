package com.example.zeebooks.commons.network

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_home.domain.model.CategoryModel

import okhttp3.MultipartBody
import retrofit2.http.*


interface ApiService {

    // USERS
    @POST("users/createUser")
    suspend fun createUser(@Body registerRequest: RegisterRequest): User

    @POST("users/login")
    suspend fun login(@Query("email") email: String, @Query("password") password: String): User

    @GET("users/getById/user")
    suspend fun getUserById(@Query("id") userid: String): User

    @GET("users/allUsers")
    suspend fun getAllUsers(): List<User>

    @GET("users/nonAdminUserCount")
    suspend fun getNumberOfUsers(): Int


    @DELETE("users/deleteUser/{id}")
    suspend fun deleteUserById(@Path ("id") id: String): User

    // CATEGORIES
    @GET("category/allCategories")
    suspend fun getCategories(): List<CategoryModel>

    @DELETE("category/deleteCategories/{id}")
    suspend fun deleteCategoryById(@Path ("id") id: String): CategoryModel


    @Multipart
    @POST("category/addCategory")
    suspend fun addCategory(@Part imagine: MultipartBody.Part, @Body categoryModel: CategoryModel): CategoryModel


}
