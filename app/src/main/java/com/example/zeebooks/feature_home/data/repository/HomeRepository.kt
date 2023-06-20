package com.example.zeebooks.feature_home.data.repository

import com.example.zeebooks.feature_home.domain.model.CategoryModel

interface HomeRepository {

    suspend fun getCategories(): Result<CategoryModel>
}