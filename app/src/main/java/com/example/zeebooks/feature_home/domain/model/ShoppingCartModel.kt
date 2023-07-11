package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ShoppingCartModel(
    val id: Long,
    val cartItems: List<ShoppingCartItemModel>,
    val userId: Long,
    val books: List<BookModel>,
    val orderBook: OrderBookModel

): Parcelable


