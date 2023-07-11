package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import com.example.zeebooks.commons.domain.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderBookModel(
    val id: Long,
    val name: String,
    val shoppingCart: ShoppingCartModel,
    val user: User,
    val orderItems: List<OrderItemModel>,
    val totalPrice: Double,
    val status: String
): Parcelable