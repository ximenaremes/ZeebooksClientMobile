package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getCategories(): Result<List<CategoryModel>> {
        return homeRepository.getCategories()
    }
}