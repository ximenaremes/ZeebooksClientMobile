package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.BookModel
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getBookById(id :String): Result<BookModel> {
        return homeRepository.getBookById(id)
    }
}