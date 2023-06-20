package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend fun execute(lastName: String,firstName: String,email: String,password: String, role: String, dateOfJoin: String) {
        return onboardingRepository.registerUserToFirebaseAuth(lastName,firstName,email,password, role, dateOfJoin)
    }

}