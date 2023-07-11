package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.WishListModel
import javax.inject.Inject

class AddFavouriteBookUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun addFavourite(userId: String, bookId: String): Result<WishListModel> {
        return homeRepository.addFavourite(userId, bookId)
    }
}