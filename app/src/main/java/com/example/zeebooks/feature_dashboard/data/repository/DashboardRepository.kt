package com.example.zeebooks.feature_dashboard.data.repository

import com.example.zeebooks.commons.domain.model.User

interface DashboardRepository {

    suspend fun getAllUsers(): Result<User>
}