package com.example.zeebooks.feature_home.data.repository

import com.example.zeebooks.feature_home.data.source.HomeRemoteDataSource
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import javax.inject.Inject

class HomeRepositoryImpl @Inject internal constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
):HomeRepository {

    override suspend fun getCategories(): Result<CategoryModel> {
        return homeRemoteDataSource.getCategories()
    }
}