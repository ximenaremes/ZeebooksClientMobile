package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val id :String,
    val name: String,
    val image:String,
//    val numberOfBook: Int


): Parcelable

