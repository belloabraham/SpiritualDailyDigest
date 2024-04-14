import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import di.appModule
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.feature.home.HomeScreen
import org.cccsharonparish.feature.onboarding.IOnboardingScreen
import org.cccsharonparish.feature.onboarding.getOnboardingScreen
import org.cccsharonparish.feature.permission.getPermissionScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import screen.FeatureScreen
import screen.onboarding.getAListOfOnboardingPageUIStates
import screen.onboarding.getOnboardingPageFooterUIState
import screen.permission.GetPermissionUIState
import screen.registerFeatureScreens
import theme.AppTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {

        registerFeatureScreens(
            onboardingScreen = GetOnboardingScreen(),
            homeScreen = HomeScreen(),
            permissionScreen = getPermissionScreen(permissionUIState = GetPermissionUIState())
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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GetOnboardingScreen(): IOnboardingScreen {
    val windowSizeClass = calculateWindowSizeClass()
    val onboardingPageUIStates = getAListOfOnboardingPageUIStates(windowSizeClass)
    val onboardingPageFooterUIState = getOnboardingPageFooterUIState()
    return getOnboardingScreen(
        uiState = onboardingPageUIStates,
        footerUiState = onboardingPageFooterUIState,
    )
}