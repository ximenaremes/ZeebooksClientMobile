package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import javax.inject.Inject

class DeleteBookFavouriteUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun removeFavourite(userId: String, bookId: String) {
        return homeRepository.removeFavourite(userId, bookId)
    }
}