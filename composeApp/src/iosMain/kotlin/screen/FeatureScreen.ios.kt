package screen

import cafe.adriel.voyager.core.registry.ScreenRegistry
import org.cccsharonparish.feature.home.HomeScreen
import org.cccsharonparish.feature.onboarding.IOnboardingScreen
import org.cccsharonparish.feature.permission.IPermissionScreen

actual fun registerFeatureScreens(
    onboardingScreen: IOnboardingScreen,
    homeScreen: HomeScreen,
    permissionScreen: IPermissionScreen,
) {
    ScreenRegistry {
        register<FeatureScreen.Home> {
            homeScreen
        }
        onboardingScreen.nextScreen = this.get(FeatureScreen.Home)
        register<FeatureScreen.Onboarding> {
            onboardingScreen
        }
    }
}