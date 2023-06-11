package com.example.zeebooks.commons.domain.model

import java.util.*

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val numberPhone: String,
    val email: String,
    val password: String,
    val role: String,
    val dateOfJoin: String,
    val image: Byte,
    val lastActivity: Date
)

