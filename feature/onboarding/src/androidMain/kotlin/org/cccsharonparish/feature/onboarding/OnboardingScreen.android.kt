package org.cccsharonparish.feature.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator


actual fun getOnboardingScreen(
    uiState: List<OnboardingPageUIState>,
    footerUiState: OnboardingPageFooterUIState,
): IOnboardingScreen {
    return OnboardingScreen(uiState = uiState, footerUiState = footerUiState)
}


class OnboardingScreen(
    private val uiState: List<OnboardingPageUIState>,
    private val footerUiState: OnboardingPageFooterUIState,
) : IOnboardingScreen {
    override var nextScreen: Screen? = null

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        OnBoardingPage(uiState = uiState, footerUiState = footerUiState) {
               navigator?.replace(nextScreen!!)
        }
    }

}

