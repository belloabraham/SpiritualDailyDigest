package screen.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import screen.home.HomeScreen

actual fun getOnboardingScreen(
) :Screen{
    return OnboardingScreen()
}

class OnboardingScreen(
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        OnBoardingPage() {
            navigator?.replace(HomeScreen())
        }
    }

}