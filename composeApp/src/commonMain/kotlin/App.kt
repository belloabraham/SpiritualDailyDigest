import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import di.appModule
import di.screenModelModule
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import screen.FeatureScreen
import screen.home.HomeScreen
import screen.onboarding.getOnboardingScreen
import screen.permission.getPermissionUIState
import screen.permission.getPermissionScreen
import screen.registerFeatureScreens
import theme.AppTheme

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule + screenModelModule)
    }) {

        registerFeatureScreens(
            onboardingScreen = getOnboardingScreen(),
            homeScreen = HomeScreen(),
            permissionScreen = getPermissionScreen(permissionUIState = getPermissionUIState())
        )

        AppTheme(isSystemInDarkTheme()) {
            val userExitedOnboardingScreen = koinInject<IPreferenceRepo>().getUserExitedOnboardingScreen()
            val onboardingScreen = rememberScreen(FeatureScreen.Onboarding)
            val homeScreen = rememberScreen(FeatureScreen.Home)


            if (userExitedOnboardingScreen) {
                Navigator(homeScreen)
            }

            if (!userExitedOnboardingScreen) {
                Navigator(onboardingScreen)
            }

        }
    }
}
