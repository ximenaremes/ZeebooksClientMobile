package com.example.zeebooks.feature_onboarding.data.repository

import com.example.zeebooks.commons.domain.model.request.LoginRequest
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.domain.model.response.LoginResponse
import com.example.zeebooks.commons.domain.model.response.RegisterResponse

interface OnboardingRepository {

    suspend fun createUser(registerRequest: RegisterRequest): Result<RegisterResponse>

    suspend fun loginUser(loginRequest: LoginRequest): Result<LoginResponse>

}