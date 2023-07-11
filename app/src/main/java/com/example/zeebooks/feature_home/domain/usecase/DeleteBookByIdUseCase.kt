package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.BookModel
import javax.inject.Inject


class DeleteBookByIdUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun deleteBookById(id: String) : Result<BookModel> {
        return homeRepository.deleteBookById(id)
    }
}