package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.BookModel
import javax.inject.Inject

class GetBookByCategoryIdUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getBookByCategoryId(id :String): Result<List<BookModel>> {
        return homeRepository.getBookByCategoryId(id)
    }
}