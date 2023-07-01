package com.example.zeebooks.feature_home.data.repository

import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import retrofit2.http.Path

interface HomeRepository {

    suspend fun getCategories(): Result<List<CategoryModel>>
    suspend fun getBooksByYear(yearOfAppearance : String) : Result<List<BookModel>>
    suspend fun getBooksByRating( ratingb: String) : Result<List<BookModel>>
    suspend fun getBookByCategoryId( id: String) : Result<List<BookModel>>
    suspend fun getBookById( id: String) : Result<BookModel>




}