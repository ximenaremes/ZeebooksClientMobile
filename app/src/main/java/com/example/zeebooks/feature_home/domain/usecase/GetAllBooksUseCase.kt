package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.domain.model.BookModel
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getAllBooks(): Result<List<BookModel>>{
        return homeRepository.getAllBooks()
    }
}