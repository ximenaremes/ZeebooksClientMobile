package com.example.zeebooks.feature_dashboard.data.repository


import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_dashboard.data.source.DashboardRemoteDataSource
import com.example.zeebooks.feature_home.domain.model.CategoryModel

import okhttp3.MultipartBody
import javax.inject.Inject

class DashboardRepositoryImpl @Inject internal constructor(

    private val dashboardRemoteDataSource: DashboardRemoteDataSource
) : DashboardRepository {

    override suspend fun getAllUsers(): Result<List<User>> {
        return dashboardRemoteDataSource.getAllUsers()
    }

    override suspend fun deleteUserById(id:String): Result<User> {
        return dashboardRemoteDataSource.deleteUserById(id)
    }

    override suspend fun addUser(registerRequest: RegisterRequest): Result<User> {
        return dashboardRemoteDataSource.addUser(registerRequest)
    }

    override suspend fun deleteCategoryById(id: String): Result<CategoryModel> {
        return dashboardRemoteDataSource.deleteCategoryById(id)
    }

    override suspend fun addCategory(imagine: MultipartBody.Part, categoryModel: CategoryModel): Result<CategoryModel> {
        return dashboardRemoteDataSource.addCategory(imagine,categoryModel)
    }

    override suspend fun getNumberOfUsers(): Result<Int> {
        return dashboardRemoteDataSource.getNumberOfUsers()
    }
}