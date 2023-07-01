package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LanguageModel(

    val id: Long,
    val name: String,
    val books: List<BookModel>
): Parcelable