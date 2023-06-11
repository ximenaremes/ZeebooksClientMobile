package com.example.zeebooks.commons.domain.model.response

import com.example.zeebooks.commons.domain.model.User

data class RegisterResponse(
    val message: String,
    val user: User,
)