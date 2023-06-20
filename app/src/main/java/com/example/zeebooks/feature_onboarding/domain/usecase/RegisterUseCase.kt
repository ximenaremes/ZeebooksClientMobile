package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
){

    suspend fun registerUser(registerRequest: RegisterRequest): Result<User> {
        return onboardingRepository.createUser(registerRequest)
    }
}


