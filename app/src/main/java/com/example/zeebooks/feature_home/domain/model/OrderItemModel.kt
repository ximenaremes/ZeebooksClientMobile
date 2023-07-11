package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderItemModel (

    val id: Long,
    val order: OrderBookModel,
    val book: BookModel,
    val quantity: Int,
    val price: Double
): Parcelable

