package com.example.zeebooks.feature_home.domain.usecase

import com.example.zeebooks.feature_home.data.repository.HomeRepository
import javax.inject.Inject

class GetTotalBooksUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun getTotalBooks(): Result<Int>{
        return homeRepository.getTotalBooks()
    }
}