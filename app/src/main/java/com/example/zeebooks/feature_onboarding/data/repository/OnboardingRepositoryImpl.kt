package com.example.zeebooks.feature_onboarding.data.repository

import com.example.zeebooks.commons.domain.model.request.LoginRequest
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.domain.model.response.LoginResponse
import com.example.zeebooks.feature_onboarding.data.source.OnboardingRemoteDataSource
import com.example.zeebooks.commons.domain.model.response.RegisterResponse

import javax.inject.Inject

class OnboardingRepositoryImpl @Inject internal constructor(
    private val onboardingRemoteDataSource: OnboardingRemoteDataSource
) : OnboardingRepository {

    override suspend fun createUser(registerRequest: RegisterRequest): Result<RegisterResponse> {
        return onboardingRemoteDataSource.createUser(registerRequest)
    }

    override suspend fun loginUser(loginRequest: LoginRequest): Result<LoginResponse>{
        return onboardingRemoteDataSource.loginUser(loginRequest)
    }

}
