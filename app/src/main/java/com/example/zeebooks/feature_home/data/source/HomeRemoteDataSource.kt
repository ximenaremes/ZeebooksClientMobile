package com.example.zeebooks.feature_home.data.source

import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getCategories(): Result<List<CategoryModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getCategories()
            }
        }
    }
}