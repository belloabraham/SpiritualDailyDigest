package dev.bellab.feature.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.cccsharonparish.core.resources.ui.Size


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage(
    currentPage: Int,
    nextPage: Int,
    totalPage: Int,
    windowSizeClass: WindowSizeClass,
    uiState: OnboardingUIState,
    onSkip: () -> Unit, onNext: () -> Unit, onPrev: () -> Unit,
) {
    Column(
        Modifier
            .padding(Size.MarginPaddingMedium(windowSizeClass))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OnboardingHeader(uiState.title, uiState.subTitle, windowSizeClass)
        OnboardingImage(
            uiState.description,
            uiState.imageRes,
            windowSizeClass
        )
        OnboardingFooter(
            currentPage = currentPage,
            nextPage = nextPage,
            totalPage = totalPage,
            windowSizeClass,
            onSkip = onSkip,
            onNext = onNext,
            onPrev = onPrev,
        )
    }
}
