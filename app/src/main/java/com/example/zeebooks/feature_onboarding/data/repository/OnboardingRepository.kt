package com.example.zeebooks.feature_onboarding.data.repository

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.domain.model.response.RegisterResponse

interface OnboardingRepository {

    suspend fun createUser(registerRequest: RegisterRequest): Result<RegisterResponse>

    suspend fun loginUser(email: String, password: String): Result<User>

}