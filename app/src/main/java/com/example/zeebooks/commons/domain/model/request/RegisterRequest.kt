package com.example.zeebooks.commons.domain.model.request

data class RegisterRequest(
    val lastName: String,
    val firstName: String,
    val email: String,
    val password: String,
    val numberPhone:String,
    val role: String,
    val dateOfJoin: String,
)
