package com.example.zeebooks.feature_home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_dashboard.domain.usecase.AddCategoryUseCase
import com.example.zeebooks.feature_dashboard.domain.usecase.DeleteCategoryByIdUseCase
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import com.example.zeebooks.feature_home.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
  private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
  private val deleteCategoryByIdUseCase: DeleteCategoryByIdUseCase,
  private val addCategoryUseCase: AddCategoryUseCase
) : BaseViewModel() {

    private val _categoriesLiveData = MutableLiveData<Result<List<CategoryModel>>>()
    val categoriesLiveData: LiveData<Result<List<CategoryModel>>> = _categoriesLiveData


    init {
        getAllCategories()
    }

    fun getAllCategories() {
        viewModelScope.launch {
            val result = getAllCategoriesUseCase.getCategories()
            _categoriesLiveData.value = result
        }
    }

    fun deleteCategoryById(id: String) {
        viewModelScope.launch {
            deleteCategoryByIdUseCase.deleteCategoryById(id)
            getAllCategories()
        }
    }

//    fun addCategory(imagine: Unit, categoryModel: CategoryModel) {
//        viewModelScope.launch {
//            addCategoryUseCase.addCategory(imagine,categoryModel)
//            getAllCategories()
//        }
//    }

}