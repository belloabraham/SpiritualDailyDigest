package screen.onboarding

import android.os.Build
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.cccsharonparish.core.common.helpers.notification.Notification
import org.cccsharonparish.spiritualdailydigest.applicationContext
import screen.home.HomeScreen
import screen.notification.OnboardingNotificationTimeScreen
import screen.permission.PermissionScreen
import screen.permission.getPermissionUIState

actual fun getOnboardingScreen(): Screen{
    return OnboardingScreen()
}


class OnboardingScreen(
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val permissionUIState = getPermissionUIState()
        val onboardingNotificationTimeScreen = OnboardingNotificationTimeScreen()
        OnBoardingPage {
            val deviceRequiresNotificationPermission =
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            val nextScreen =
                if (deviceRequiresNotificationPermission && !Notification.isPermissionGranted(
                        applicationContext
                    )
                ) {
                    val permissionScreen =
                        PermissionScreen(permissionUIState, Notification.PERMISSION)
                    permissionScreen.nextScreen = onboardingNotificationTimeScreen
                    permissionScreen
                } else {
                    onboardingNotificationTimeScreen
                }
            navigator?.replace(nextScreen)
        }
    }

}

