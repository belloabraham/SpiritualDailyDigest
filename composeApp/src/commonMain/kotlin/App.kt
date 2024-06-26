import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import di.appModule
import di.screenModelModule
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import screen.home.HomeScreen
import screen.notification.NotificationTimeScreen
import screen.onboarding.getOnboardingScreen
import theme.AppTheme

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule + screenModelModule)
    }) {

        AppTheme(isSystemInDarkTheme()) {
            val userExitedOnboardingScreen =
                koinInject<IPreferenceRepo>().getUserExitedOnboardingScreen()

            val nextScreen =
                if (userExitedOnboardingScreen) HomeScreen("") else getOnboardingScreen()

            Navigator(nextScreen) { navigator ->
                SlideTransition(navigator)
            }

        }
    }
}
