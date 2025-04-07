package screen.onboarding

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.cccsharonparish.core.common.helpers.notification.Notification
import org.cccsharonparish.core.domain.applicationContext
import screen.notification.OnboardingNotificationTimeScreen
import screen.permission.getPermissionScreen
import screen.permission.getPermissionUIState

actual fun getOnboardingScreen(contentId: String?): Screen {
    return OnboardingScreen(contentId)
}


class OnboardingScreen(private val contentId: String?) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val onboardingNotificationTimeScreen = OnboardingNotificationTimeScreen(contentId)
        val deviceRequiresNotificationPermission =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        val permission = if (deviceRequiresNotificationPermission) Notification.PERMISSION else null
        val permissionUIState by getPermissionUIState(permission)

        OnBoardingPage {
            val nextScreen =
                if (deviceRequiresNotificationPermission && !Notification.isPermissionGranted(
                        applicationContext
                    )
                ) {
                    val permissionScreen = getPermissionScreen(permissionUIState)
                    permissionScreen.nextScreen = onboardingNotificationTimeScreen
                    permissionScreen
                } else {
                    onboardingNotificationTimeScreen
                }
            navigator?.replace(nextScreen)
        }
    }

}

