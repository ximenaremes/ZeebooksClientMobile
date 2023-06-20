package com.example.zeebooks.feature_home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_home.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
  private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : BaseViewModel() {

    fun getAllCategories(){
      viewModelScope.launch {
        getAllCategoriesUseCase.getCategories()
      }
    }
}