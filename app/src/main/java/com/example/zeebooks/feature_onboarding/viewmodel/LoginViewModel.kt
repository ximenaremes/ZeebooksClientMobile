package com.example.zeebooks.feature_onboarding.viewmodel

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Constants.DEFAULT_VALUE
import com.example.zeebooks.commons.utils.Constants.PASSWORD_PATTERN
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_onboarding.domain.usecase.LoginUseCase
import com.example.zeebooks.feature_onboarding.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val loginUserUseCase: LoginUserUseCase
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
        onSuccessAdminCallback: (User) -> Unit,
        onSuccessUserCallback: () -> Unit,
        onErrorCallback: (String) -> Unit,

        ) {
        viewModelScope.launch {
            loginUseCase.loginUser(email, password).fold(
                onSuccess = { user ->
                    Timber.tag("User").d("SUCCESS Login")

                    if (user.role == "ADMIN") {
                        onSuccessAdminCallback.invoke(user)
                    } else {
                        onSuccessUserCallback.invoke()
                    }
                },
                onFailure = {
                    Timber.tag("User").d("ERROR Login")
                    onErrorCallback.invoke("Emailul sau parola introduse sunt incorecte!")
                }
            )
        }
    }

    fun loginUserToFirebase(
        email: String, password: String
    ){
            viewModelScope.launch {
                loginUserUseCase.execute(email,password)
    }
    }
}