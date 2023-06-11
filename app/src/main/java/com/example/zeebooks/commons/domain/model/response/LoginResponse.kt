package com.example.zeebooks.commons.domain.model.response

import com.example.zeebooks.commons.domain.model.User

data class LoginResponse (
    val message: String,
    val user: User
)