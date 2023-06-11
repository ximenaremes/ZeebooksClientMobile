package com.example.zeebooks.commons.domain.model.request

data class LoginRequest(
    val email: String,
    val password: String,
)