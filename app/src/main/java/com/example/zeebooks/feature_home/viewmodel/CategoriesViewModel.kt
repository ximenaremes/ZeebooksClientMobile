package com.example.zeebooks.feature_home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_dashboard.domain.usecase.AddCategoryUseCase
import com.example.zeebooks.feature_dashboard.domain.usecase.DeleteCategoryByIdUseCase
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import com.example.zeebooks.feature_home.domain.usecase.GetAllCategoriesUseCase
import com.example.zeebooks.feature_home.domain.usecase.GetBookByCategoryIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
  private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
  private val deleteCategoryByIdUseCase: DeleteCategoryByIdUseCase,
  private val addCategoryUseCase: AddCategoryUseCase,
  private val getBookByCategoryIdUseCase: GetBookByCategoryIdUseCase
) : BaseViewModel() {

    private val _categoriesLiveData = MutableLiveData<Result<List<CategoryModel>>>()
    val categoriesLiveData: LiveData<Result<List<CategoryModel>>> = _categoriesLiveData

    private val _categories = MutableLiveData<Result<List<BookModel>>>()
    val categories: LiveData<Result<List<BookModel>>> = _categories


    init {
        getAllCategories()
    }

    fun getAllCategories() {
        viewModelScope.launch {
            val result = getAllCategoriesUseCase.getCategories()
            _categoriesLiveData.value = result
        }
    }

    fun getBookByCategoryId(id:String) {
        viewModelScope.launch {
            val result = getBookByCategoryIdUseCase.getBookByCategoryId(id)
            _categories.value = result
        }
    }

    fun deleteCategoryById(id: String) {
        viewModelScope.launch {
            deleteCategoryByIdUseCase.deleteCategoryById(id)
            getAllCategories()
        }
    }

    fun validation(
        name: String
    ): Int {

        if (name.isEmpty()) {
            return Constants.VALID_LASTNAME
        }
        return Constants.DEFAULT_VALUE
    }



}