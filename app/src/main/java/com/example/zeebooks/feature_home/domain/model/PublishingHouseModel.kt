package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PublishingHouseModel(

    val id: Long,
    val name: String,
    val books: List<BookModel>
): Parcelable