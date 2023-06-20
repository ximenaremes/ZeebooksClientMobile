package com.example.zeebooks.feature_dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_dashboard.domain.usecase.GetAllUsersUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase

) : BaseViewModel() {


    fun getAllUsers(){
        viewModelScope.launch {
            getAllUsersUseCase.getAllUsers()
        }
    }

}