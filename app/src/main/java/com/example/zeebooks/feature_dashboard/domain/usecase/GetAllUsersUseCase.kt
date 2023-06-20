package com.example.zeebooks.feature_dashboard.domain.usecase

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun getAllUsers(): Result<User> {
        return dashboardRepository.getAllUsers()
    }
}