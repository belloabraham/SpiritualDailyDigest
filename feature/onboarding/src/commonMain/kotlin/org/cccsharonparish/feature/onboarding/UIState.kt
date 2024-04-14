package org.cccsharonparish.feature.onboarding

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

data class OnboardingPageUIState @OptIn(ExperimentalResourceApi::class) constructor(
    val title:String,
    val subTitle:String,
    val description:String,
    val imageRes:DrawableResource,
):JavaSerializable

data class OnboardingPageFooterUIState(
    val next :String,
    val back :String,
    val skip :String,
    val getStarted :String,
):JavaSerializable
