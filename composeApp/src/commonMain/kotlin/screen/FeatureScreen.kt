package screen

import cafe.adriel.voyager.core.registry.ScreenProvider
import org.cccsharonparish.feature.home.HomeScreen
import org.cccsharonparish.feature.onboarding.IOnboardingScreen
import org.cccsharonparish.feature.permission.IPermissionScreen

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
