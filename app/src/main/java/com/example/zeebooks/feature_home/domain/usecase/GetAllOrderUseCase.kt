package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.OrderBookModel
import javax.inject.Inject



class GetAllOrderUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getAllOrder(): Result<List<OrderBookModel>>{
        return homeRepository.getAllOrder()
    }
}