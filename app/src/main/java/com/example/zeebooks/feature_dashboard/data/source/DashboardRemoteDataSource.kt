package com.example.zeebooks.feature_dashboard.data.source

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class DashboardRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getAllUsers(): Result<List<User>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getAllUsers()
            }
        }
    }

    suspend fun deleteUserById(id :String): Result<User>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.deleteUserById(id)
            }
        }
    }

    suspend fun addUser(registerRequest: RegisterRequest): Result<User>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.createUser(registerRequest)
            }
        }
    }

    suspend fun deleteCategoryById(id :String): Result<CategoryModel>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.deleteCategoryById(id)
            }
        }
    }

    suspend fun addCategory(imagine: MultipartBody.Part, categoryModel: CategoryModel): Result<CategoryModel>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.addCategory(imagine, categoryModel)
            }
        }
    }

    suspend fun getNumberOfUsers(): Result<Int>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getNumberOfUsers()
            }
        }
    }
}