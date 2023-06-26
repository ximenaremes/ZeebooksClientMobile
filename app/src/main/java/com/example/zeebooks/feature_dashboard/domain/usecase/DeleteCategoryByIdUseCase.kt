package com.example.zeebooks.feature_dashboard.domain.usecase

import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import javax.inject.Inject


class DeleteCategoryByIdUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun deleteCategoryById(id :String): Result<CategoryModel> {
        return dashboardRepository.deleteCategoryById(id)
    }
}