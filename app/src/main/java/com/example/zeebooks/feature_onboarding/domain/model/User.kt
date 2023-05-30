package com.example.zeebooks.feature_onboarding.domain.model

import android.os.Parcelable
import java.util.Date

@kotlinx.parcelize.Parcelize
//data class User(val username: String, val email: String, val password: String) : Parcelable

data class User(
    val id:Int,
    val firstName: String,
    val lastName: String
//    val numberPhone: String,
//    val email: String,
//    val password: String,
//    val role: String,
//    val image: Byte,
//    val lastActivity: Date
) : Parcelable

