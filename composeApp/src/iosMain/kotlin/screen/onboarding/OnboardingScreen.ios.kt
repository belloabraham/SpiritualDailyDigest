package screen.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import screen.notification.OnboardingNotificationTimeScreen

actual fun getOnboardingScreen(contentId: String?) :Screen{
    return OnboardingScreen(contentId)
}

class OnboardingScreen(private val contentId: String?) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        OnBoardingPage() {
            navigator?.replace(OnboardingNotificationTimeScreen(contentId))
        }
    }

}