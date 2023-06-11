package com.example.zeebooks.feature_onboarding.viewmodel

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.domain.model.request.LoginRequest
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Constants.DEFAULT_VALUE
import com.example.zeebooks.commons.utils.Constants.PASSWORD_PATTERN
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_onboarding.domain.usecase.LoginUseCase
import com.example.zeebooks.feature_onboarding.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    fun validation(email: String, password: String): Int {
        if (email.isEmpty()) {
            return Constants.VALID_EMAIL
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Constants.VALID_EMAIL_CHARACTERS
        } else if (password.isEmpty()) {
            return Constants.VALID_PASSWORD
        } else if (password.length <= 8) {
            return Constants.VALID_PASSWORD_LENGTH
        } else if (!password.matches(PASSWORD_PATTERN.toRegex())) {
            return Constants.VALID_PASSWORD_CHARACTERS
        }
        return DEFAULT_VALUE
    }

    fun loginUser(
        email: String,
        password: String,

    ) {
        viewModelScope.launch {
            val loginRequest =
                LoginRequest(email, password)
            loginUseCase.loginUser(loginRequest).fold(
                onSuccess = {
                    Timber.e("succes register ")
                },
                onFailure = {
                    Timber.e("error register")

                }
            )
        }
    }
}