package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ShoppingCartItemModel(
    val id: Long,
    val shoppingCart: ShoppingCartModel,
    val book: BookModel,
    val quantity: Int,
    val price: Double

): Parcelable

