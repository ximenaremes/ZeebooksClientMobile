package com.example.zeebooks.feature_onboarding.domain.usecase

import com.example.zeebooks.commons.domain.usecase.AbstractUseCase
import com.example.zeebooks.commons.viewmodel.model.DataModel
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class RegisterUseCaseArgs(
    val firstNmae: String,
    val lastName: String
)

class RegisterUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : AbstractUseCase<RegisterUseCaseArgs, Boolean>() {

    override fun execute(args: RegisterUseCaseArgs): Flow<DataModel<Boolean>> {
        return flow {
            onboardingRepository.createUser(args.firstNmae, args.lastName)
            DataModel(data = true)
        }
    }
}