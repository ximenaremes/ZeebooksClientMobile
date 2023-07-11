package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import javax.inject.Inject



class AddOrderUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun addOrder(userId: String): Result<String> {
        return homeRepository.addOrder(userId)
    }
}