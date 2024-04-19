package screen

import cafe.adriel.voyager.core.registry.ScreenRegistry
import screen.home.HomeScreen
import screen.onboarding.IOnboardingScreen
import screen.permission.IPermissionScreen

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