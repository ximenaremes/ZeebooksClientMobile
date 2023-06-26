package com.example.zeebooks.feature_dashboard.domain.usecase


import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import okhttp3.MultipartBody
import javax.inject.Inject


class AddCategoryUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun addCategory(
        imagine: MultipartBody.Part,
        categoryModel: CategoryModel
    ): Result<CategoryModel> {
        return dashboardRepository.addCategory(imagine, categoryModel)
    }
}