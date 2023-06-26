package com.example.zeebooks.feature_dashboard.domain.usecase

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import javax.inject.Inject


class DeleteUserByIdUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun deleteUserById(id :String): Result<User> {
        return dashboardRepository.deleteUserById(id)
    }
}