package screen.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import screen.notification.OnboardingNotificationTimeScreen

actual fun getOnboardingScreen(contentUrl: String?) :Screen{
    return OnboardingScreen(contentUrl)
}

class OnboardingScreen(private val contentUrl: String?) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        OnBoardingPage() {
            navigator?.replace(OnboardingNotificationTimeScreen(contentUrl))
        }
    }

}