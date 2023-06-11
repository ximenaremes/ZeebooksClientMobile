package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.domain.model.response.RegisterResponse
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
){

    suspend fun registerUser(registerRequest: RegisterRequest): Result<RegisterResponse> {
        return onboardingRepository.createUser(registerRequest)
    }
}


