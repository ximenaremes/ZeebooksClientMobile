package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend fun execute(email: String, password: String) {
        return onboardingRepository.login(email,password)
    }

}