package com.example.zeebooks.feature_dashboard.domain.usecase

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun addUser(registerRequest: RegisterRequest): Result<User> {
        return dashboardRepository.addUser(registerRequest)
    }
}