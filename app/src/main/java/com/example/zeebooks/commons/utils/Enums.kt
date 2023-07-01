package com.example.zeebooks.commons.utils

import com.example.zeebooks.R

enum class Enums (
    val stringResource: Int) {
    VALID_LASTNAME(R.string.textErroLastName) ,
    VALID_FIRSTNAME(R.string.textErroFirstName) ,
    VALID_EMAIL(R.string.textErroEmail),
    VALID_EMAIL_CHARACTERS(R.string.textErroEmailCharacters),
    VALID_PASSWORD(R.string.textErroPassword),
    VALID_PASSWORD_LENGTH(R.string.textErroPasswordLength),
    VALID_PASSWORD_CHARACTERS(R.string.textErroPasswordCharacters),
    VALID_CONFIRM_PASSWORD(R.string.textErroConfPassword),
    VALID_CONFIRM_PASSWORD_IDENTICAL(R.string.textErroConfPasswordIdentical),
    VALID_NUMBER_PHONE(R.string.textErroNumber),
    VALID_NUMBER_PHONE_LENGTH(R.string.textErrorNumberLength)



}