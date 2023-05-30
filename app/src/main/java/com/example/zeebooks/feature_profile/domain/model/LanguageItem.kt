package com.example.zeebooks.feature_profile.domain.model

data class LanguageItem(
    val id: Int,
    val name: String,
    var isSelected : Boolean = false
)

enum class LanguageItemType(val id: Int) {
    ROMANA(0),
    ENGLEZA(1),
    FRANCEZA(2)
}