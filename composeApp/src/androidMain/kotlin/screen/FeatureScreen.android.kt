package screen

import android.os.Build
import cafe.adriel.voyager.core.registry.ScreenRegistry
import org.cccsharonparish.spiritualdailydigest.applicationContext
import org.cccsharonparish.core.common.helpers.notification.Notification
import screen.home.HomeScreen
import screen.onboarding.IOnboardingScreen
import screen.permission.IPermissionScreen

actual fun registerFeatureScreens(
    onboardingScreen: IOnboardingScreen,
    homeScreen: HomeScreen,
    permissionScreen: IPermissionScreen,
) {
    val deviceRequiresNotificationPermission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    ScreenRegistry {
        register<FeatureScreen.Home> {
            homeScreen
        }
        val nextScreen =
            if (deviceRequiresNotificationPermission && !Notification.isPermissionGranted(
                    applicationContext)
            ) {
                permissionScreen.nextScreen = this.get(FeatureScreen.Home)
                permissionScreen.minSDKVersion = Build.VERSION_CODES.TIRAMISU
                permissionScreen.permission = Notification.PERMISSION
                register<FeatureScreen.Permission> {
                    permissionScreen
                }
                this.get(FeatureScreen.Permission)
            } else {
                this.get(FeatureScreen.Home)
            }
        onboardingScreen.nextScreen = nextScreen
        register<FeatureScreen.Onboarding> {
            onboardingScreen
        }
    }
}