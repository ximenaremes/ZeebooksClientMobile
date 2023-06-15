package com.example.zeebooks.commons.domain.model.response


data class RegisterResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val numberPhone: String,
    val email: String,
    val password: String,
    val role: String,
    val dateOfJoin: String,
)