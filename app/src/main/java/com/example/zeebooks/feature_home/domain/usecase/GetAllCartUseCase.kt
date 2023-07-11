package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.ShoppingCartItemModel
import javax.inject.Inject

class GetAllCartUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

//    suspend fun getAllCart(userId: String) : Result<List<BookModel>>{
//        return homeRepository.getAllCart(userId)
//    }
}