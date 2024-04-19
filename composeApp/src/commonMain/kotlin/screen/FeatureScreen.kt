package screen

import cafe.adriel.voyager.core.registry.ScreenProvider
import screen.home.HomeScreen
import screen.onboarding.IOnboardingScreen
import screen.permission.IPermissionScreen

sealed class FeatureScreen : ScreenProvider {
    data object Onboarding : FeatureScreen()
    data object Home : FeatureScreen()
    data object Permission : FeatureScreen()

}

expect fun registerFeatureScreens(
    onboardingScreen: IOnboardingScreen,
    homeScreen: HomeScreen,
    permissionScreen: IPermissionScreen,
)
