package com.example.zeebooks.feature_dashboard.data.repository

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import okhttp3.MultipartBody
import retrofit2.http.Query

interface DashboardRepository {

    suspend fun getAllUsers(): Result<List<User>>
    suspend fun deleteUserById( id:String ): Result<User>
    suspend fun addUser(registerRequest: RegisterRequest):Result<User>
    suspend fun deleteCategoryById(id:String):Result<CategoryModel>
    suspend fun addCategory(image: MultipartBody.Part, name:String): Result<CategoryModel>
    suspend fun getNumberOfUsers(): Result<Int>




}