package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import javax.inject.Inject

class GetTotalNumberOrderUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getTotalNumberOrder(): Result<Int>{
        return homeRepository.getTotalNumberOrder()
    }
}