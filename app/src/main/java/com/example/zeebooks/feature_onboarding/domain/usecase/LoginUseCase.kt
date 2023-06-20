package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository) {

    suspend fun loginUser(email: String, password: String): Result<User> {
        return onboardingRepository.loginUser(email,password)
    }
}