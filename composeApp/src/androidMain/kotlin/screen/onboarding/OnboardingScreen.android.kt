package screen.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator


actual fun getOnboardingScreen(
): IOnboardingScreen {
    return OnboardingScreen()
}


class OnboardingScreen(
) : IOnboardingScreen {
    override var nextScreen: Screen? = null

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        OnBoardingPage{
               navigator?.replace(nextScreen!!)
        }
    }

}

