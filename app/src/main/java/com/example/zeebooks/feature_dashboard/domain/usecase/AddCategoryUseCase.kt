package com.example.zeebooks.feature_dashboard.domain.usecase


import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import okhttp3.MultipartBody
import javax.inject.Inject


class AddCategoryUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun addCategory(
        image: MultipartBody.Part,
        name:String
    ): Result<CategoryModel> {
        return dashboardRepository.addCategory(image, name)
    }
}