package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.ShoppingCartModel
import javax.inject.Inject


class AddToCartUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun addToCart(bookId: String, userId: String){
        return homeRepository.addToCart(bookId, userId )
    }
}