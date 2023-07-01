package com.example.zeebooks.feature_home.data.repository

import com.example.zeebooks.feature_home.data.source.HomeRemoteDataSource
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import javax.inject.Inject

class HomeRepositoryImpl @Inject internal constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
):HomeRepository {

    override suspend fun getCategories(): Result<List<CategoryModel>> {
        return homeRemoteDataSource.getCategories()
    }

    override suspend fun getBooksByYear(yearOfAppearance: String): Result<List<BookModel>> {
       return homeRemoteDataSource.getBooksByYear(yearOfAppearance)
    }

    override suspend fun getBooksByRating(ratingb: String): Result<List<BookModel>> {
        return homeRemoteDataSource.getBooksByRating(ratingb)
    }

    override suspend fun getBookByCategoryId(id: String): Result<List<BookModel>> {
        return homeRemoteDataSource.getBookByCategoryId(id)
    }

    override suspend fun getBookById(id: String): Result<BookModel> {
        return homeRemoteDataSource.getBookById(id)
    }
}