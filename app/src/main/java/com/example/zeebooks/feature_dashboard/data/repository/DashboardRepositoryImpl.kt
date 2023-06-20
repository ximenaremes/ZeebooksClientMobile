package com.example.zeebooks.feature_dashboard.data.repository

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.feature_dashboard.data.source.DashboardRemoteDataSource
import javax.inject.Inject

class DashboardRepositoryImpl @Inject internal constructor(

    private val dashboardRemoteDataSource: DashboardRemoteDataSource
) : DashboardRepository {

    override suspend fun getAllUsers(): Result<User> {
        return dashboardRemoteDataSource.getAllUsers()
    }
}