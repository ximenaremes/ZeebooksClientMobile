package com.example.zeebooks.feature_dashboard.domain.usecase

import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import javax.inject.Inject


class GetNumberOfUsersUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun getNumberOfUsers(): Result<Int> {
        return dashboardRepository.getNumberOfUsers()
    }
}