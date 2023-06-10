package com.example.zeebooks.feature_onboarding.viewmodel

import android.util.Patterns
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Constants.DEFAULT_VALUE
import com.example.zeebooks.commons.utils.Constants.PASSWORD_PATTERN
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import javax.inject.Inject


class LoginViewModel @Inject constructor() : BaseViewModel() {

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
}