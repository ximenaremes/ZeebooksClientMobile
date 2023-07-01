package com.example.zeebooks.feature_home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.usecase.GetAllBooksByRatingUseCase
import com.example.zeebooks.feature_home.domain.usecase.GetAllBooksByYearUseCase
import com.example.zeebooks.feature_home.domain.usecase.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookViewModel @Inject constructor(
    private val getAllBooksByYearUseCase: GetAllBooksByYearUseCase,
    private val getAllBooksByRatingUseCase: GetAllBooksByRatingUseCase,
    private val getBookByIdUseCase: GetBookByIdUseCase

) : BaseViewModel() {

    private val _booksLiveData = MutableLiveData<Result<List<BookModel>>>()
    val booksLiveData: LiveData<Result<List<BookModel>>> = _booksLiveData

    private val _booksLRating = MutableLiveData<Result<List<BookModel>>>()
    val booksRating: LiveData<Result<List<BookModel>>> = _booksLRating

    private val _booksDetails = MutableLiveData<Result<BookModel>>()
    val bookDetails: LiveData<Result<BookModel>> = _booksDetails


    fun getAllBooksByYear(yearOfAppearance: String) {
        viewModelScope.launch {
            val result = getAllBooksByYearUseCase.getBooksByYear(yearOfAppearance)
            _booksLiveData.value = result
        }
    }

    fun getBooksByRating(ratingb: String) {
        viewModelScope.launch {
            val result = getAllBooksByRatingUseCase.getBooksByRating(ratingb)
            _booksLRating.value = result
        }
    }

    fun getBooksById(id: String, param: (Any) -> Unit) {
        viewModelScope.launch {
            val result = getBookByIdUseCase.getBookById(id)
            _booksDetails.value = result
        }
    }
}