package com.example.zeebooks.feature_dashboard.data.source

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DashboardRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getAllUsers(): Result<User>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getAllUsers()
            }
        }
    }
}