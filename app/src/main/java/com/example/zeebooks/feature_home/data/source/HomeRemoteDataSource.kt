package com.example.zeebooks.feature_home.data.source

import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import com.example.zeebooks.feature_home.domain.model.BookModel
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

    suspend fun getBooksByYear(yearOfAppearance : String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBooksByYear(yearOfAppearance )
            }
        }
    }

    suspend fun getBooksByRating(ratingb : String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBooksByRating(ratingb )
            }
        }
    }
    suspend fun getBookByCategoryId(id : String): Result<List<BookModel>>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBookByCategoryId(id )
            }
        }
    }
    suspend fun getBookById(id : String): Result<BookModel>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.getBookById(id )
            }
        }
    }

}