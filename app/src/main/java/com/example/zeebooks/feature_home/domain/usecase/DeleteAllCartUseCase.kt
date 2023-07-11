package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import javax.inject.Inject


class DeleteAllCartUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun removeAllCart(userId: String) {
        return homeRepository.removeAllCart(userId)
    }
}