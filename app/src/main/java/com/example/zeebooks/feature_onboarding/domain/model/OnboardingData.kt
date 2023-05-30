package com.example.zeebooks.feature_onboarding.domain.model

import androidx.annotation.DrawableRes

data class OnboardingData(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)
