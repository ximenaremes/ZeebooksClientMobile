package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.OrderBookModel
import javax.inject.Inject


class GetOrderByIdUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getAllOrdersById(orderId: String): Result<OrderBookModel>{
        return homeRepository.getAllOrdersById(orderId)
    }
}