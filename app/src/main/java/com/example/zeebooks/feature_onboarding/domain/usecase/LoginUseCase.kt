package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.commons.domain.model.request.LoginRequest
import com.example.zeebooks.commons.domain.model.response.LoginResponse
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor
    (private val onboardingRepository: OnboardingRepository) {

    suspend fun loginUser(loginRequest: LoginRequest): Result<LoginResponse> {
        return onboardingRepository.loginUser(loginRequest)
    }
}