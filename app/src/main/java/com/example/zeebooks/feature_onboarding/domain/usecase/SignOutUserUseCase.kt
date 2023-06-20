package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject

class SignOutUserUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend fun execute() {
        return onboardingRepository.signOut()
    }
}