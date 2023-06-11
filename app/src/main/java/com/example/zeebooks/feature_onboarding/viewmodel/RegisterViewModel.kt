package com.example.zeebooks.feature_onboarding.viewmodel


import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Constants.VALID_CONFIRM_PASSWORD
import com.example.zeebooks.commons.utils.Constants.VALID_CONFIRM_PASSWORD_IDENTICAL
import com.example.zeebooks.commons.utils.Constants.VALID_EMAIL
import com.example.zeebooks.commons.utils.Constants.VALID_EMAIL_CHARACTERS
import com.example.zeebooks.commons.utils.Constants.VALID_FIRSTNAME
import com.example.zeebooks.commons.utils.Constants.VALID_LASTNAME
import com.example.zeebooks.commons.utils.Constants.VALID_PASSWORD
import com.example.zeebooks.commons.utils.Constants.VALID_PASSWORD_CHARACTERS
import com.example.zeebooks.commons.utils.Constants.VALID_PASSWORD_LENGTH
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_onboarding.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel() {


    fun validation(
        lastName: String,
        firstName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Int {

        if (lastName.isEmpty()) {
            return VALID_LASTNAME
        } else if (firstName.isEmpty()) {
            return VALID_FIRSTNAME
        } else if (email.isEmpty()) {
            return VALID_EMAIL
        } else if (!EMAIL_ADDRESS.matcher(email).matches()) {
            return VALID_EMAIL_CHARACTERS
        } else if (password.isEmpty()) {
            return VALID_PASSWORD
        } else if (password.length <= 8) {
            return VALID_PASSWORD_LENGTH
        } else if (!password.matches(Constants.PASSWORD_PATTERN.toRegex())) {
            return VALID_PASSWORD_CHARACTERS
        } else if (confirmPassword.isEmpty()) {
            return VALID_CONFIRM_PASSWORD
        } else if (confirmPassword != password) {
            return VALID_CONFIRM_PASSWORD_IDENTICAL
        }
        return Constants.DEFAULT_VALUE
    }

    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: String,
        dateOfJoin: String,
    ) {
        viewModelScope.launch {
            val registerRequest =
                RegisterRequest(firstName, lastName, email, password, role, dateOfJoin)
            registerUseCase.registerUser(registerRequest).fold(
                onSuccess = {
                    Timber.e("success register ")
                },
                onFailure = {
                    Timber.e("error register")

                }
            )
        }
    }


}