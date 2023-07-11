package com.example.zeebooks.feature_home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookModel (

    val id :String,
    val name: String,
    val price:String,
    val nameAuthor: String,
    val image: String,
    val ratingb: String,
    val description: String,
    val numberOfPage: String,
    val numberOfCopies: String,
    val yearOfAppearance: String,
    val status: String,
    val isbn: String,

    var favorite: Boolean = false,


//    val category: CategoryModel,
//    val language: LanguageModel,
//    val publishingHouse: PublishingHouseModel
//    val rating: String,
//    val carts: String,
//    val user: String,
//    val category: String,
//    val author: String,
//    val language: String,
//    val publishingHouse: String
) : Parcelable





