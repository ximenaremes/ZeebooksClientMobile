package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.CartModel
import javax.inject.Inject


class AddCartUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun addBookToCart(userId: String, bookId: String): Result<CartModel> {
        return homeRepository.addBookToCart(userId, bookId)
    }
}