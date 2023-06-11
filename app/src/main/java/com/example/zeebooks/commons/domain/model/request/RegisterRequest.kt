package com.example.zeebooks.commons.domain.model.request

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val role: String,
    val dateOfJoin: String,
)
