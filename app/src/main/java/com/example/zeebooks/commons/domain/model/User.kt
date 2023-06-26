package com.example.zeebooks.commons.domain.model

import java.util.*

data class User(
    val id: String ?= null,
    val lastName: String ?=null,
    val firstName: String ?=null,
    val numberPhone: String ?=null,
    val email: String ?=null,
    val password: String ?=null,
    val role: String ?=null,
    val dateOfJoin: String ?=null,
    val image: Byte ?=null,
    val lastActivity: Date ?=null
)

