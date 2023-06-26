package com.example.zeebooks.feature_dashboard.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_dashboard.domain.usecase.AddUserUseCase
import com.example.zeebooks.feature_dashboard.domain.usecase.DeleteUserByIdUseCase
import com.example.zeebooks.feature_dashboard.domain.usecase.GetAllUsersUseCase
import com.example.zeebooks.feature_dashboard.domain.usecase.GetNumberOfUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val getNumberOfUsersUseCase: GetNumberOfUsersUseCase

) : BaseViewModel() {

    private val _usersLiveData = MutableLiveData<Result<List<User>>>()
    val usersLiveData: LiveData<Result<List<User>>> = _usersLiveData

    private val _numberOfUsers = MutableLiveData<Int>()
    val numberOfUsers: LiveData<Int> get() = _numberOfUsers


    init {
        getAllUsers()
        getNumberOfUsers()
    }

    fun validation(
        lastName: String,
        firstName: String,
        email: String,
        password: String,
        numberPhone:String
    ): Int {

        if (lastName.isEmpty()) {
            return Constants.VALID_LASTNAME
        } else if (firstName.isEmpty()) {
            return Constants.VALID_FIRSTNAME
        } else if (email.isEmpty()) {
            return Constants.VALID_EMAIL
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Constants.VALID_EMAIL_CHARACTERS
        } else if (password.isEmpty()) {
            return Constants.VALID_PASSWORD
        } else if (password.length <= 8) {
            return Constants.VALID_PASSWORD_LENGTH
        } else if (!password.matches(Constants.PASSWORD_PATTERN.toRegex())) {
            return Constants.VALID_PASSWORD_CHARACTERS
        } else if (numberPhone.isEmpty()) {
            return Constants.VALID_NUMBER_PHONE
        } else if (numberPhone.length == 11) {
            return Constants.VALID_NUMBER_PHONE_LENGTH
        }
        return Constants.DEFAULT_VALUE
    }


    fun getAllUsers() {
        viewModelScope.launch {
            val result = getAllUsersUseCase.getAllUsers()
            _usersLiveData.value = result
        }
    }

    fun deleteUserById(id: String) {
        viewModelScope.launch {
            deleteUserByIdUseCase.deleteUserById(id)
            getAllUsers()
            getNumberOfUsers()
        }
    }

    fun addUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            addUserUseCase.addUser(registerRequest)
                .fold(
                    onSuccess = {
//                        Timber.e("SUCCESS CREATE USER $it")
                    },
                    onFailure = {
//                        Timber.e("FAILURE CREATE USER $it")

                    }

                )
            getAllUsers()
            getNumberOfUsers()
        }
    }

    fun getNumberOfUsers() {
        viewModelScope.launch {
            val result = getNumberOfUsersUseCase.getNumberOfUsers()
            if (result.isSuccess) {
                val number = result.getOrNull() ?: 0
                _numberOfUsers.value = number
            } else {
                val error = result.exceptionOrNull()
            }
        }
    }


}