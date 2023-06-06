package com.example.zeebooks.feature_onboarding.data.repository

import com.example.zeebooks.commons.domain.model.User
import retrofit2.Response

interface OnboardingRepository {

    suspend fun createUser(firstName: String, lastName: String): Response<User>
    suspend fun loginUser(email: String, password: String): Response<User>

}