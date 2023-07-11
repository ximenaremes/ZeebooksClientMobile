package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import com.example.zeebooks.commons.domain.model.User
import kotlinx.parcelize.Parcelize


@Parcelize
class CartModel(

    val id: String,
    val userId: User,
    val bookId: BookModel

) : Parcelable