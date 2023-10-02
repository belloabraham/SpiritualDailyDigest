package dev.bellab.feature.onboarding.ui

import androidx.annotation.DrawableRes

data class OnboardingUIState(
    val title:String,
    val subTitle:String,
    val description:String,
    @DrawableRes val imageRes:Int
)
