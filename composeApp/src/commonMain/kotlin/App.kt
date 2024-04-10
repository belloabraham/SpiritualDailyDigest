import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import di.appModule
import org.cccsharonparish.core.data.preference.IDatastore
import org.cccsharonparish.core.data.preference.Key
import org.cccsharonparish.feature.onboarding.OnboardingPageFooterUIState
import org.cccsharonparish.feature.onboarding.OnboardingPageUIState
import org.cccsharonparish.feature.onboarding.OnboardingScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.rememberKoinInject
import screen.FeatureScreen
import screen.home.HomeScreen
import screen.onboarding.OnboardingImage
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.app_name
import spiritualdailydigest.composeapp.generated.resources.onboarding_title
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle
import spiritualdailydigest.composeapp.generated.resources.onboarding_description
import spiritualdailydigest.composeapp.generated.resources.back_button
//import spiritualdailydigest.composeapp.generated.resources.ap
import spiritualdailydigest.composeapp.generated.resources.lets_get_started
import spiritualdailydigest.composeapp.generated.resources.next
import spiritualdailydigest.composeapp.generated.resources.skip
import theme.AppTheme

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {

        val windowSizeClass = calculateWindowSizeClass()
        val onboardingPageUIStates = getAListOfOnboardingPageUIStates(windowSizeClass)
        val onboardingPageFooterUIState = getOnboardingPageFooterUIState()
        ScreenRegistry.register<FeatureScreen.Onboarding> {
            OnboardingScreen(
                onboardingPageUIStates,
                onboardingPageFooterUIState
            )
        }

        AppTheme(isSystemInDarkTheme()) {
            var userExitedOnboardingScreen by remember { mutableStateOf(false) }
            val dataStore = rememberKoinInject<IDatastore>()
            val onboardingScreen = rememberScreen(FeatureScreen.Onboarding)

            LaunchedEffect(Unit) {
                userExitedOnboardingScreen = dataStore.getBoolean(Key.USER_EXITED_ONBOARDING, false)
            }

            if (userExitedOnboardingScreen) {
                Navigator(HomeScreen())
            }

            if (!userExitedOnboardingScreen) {
                Navigator(onboardingScreen)
            }

        }
    }
}

@Composable
@OptIn(ExperimentalResourceApi::class)
private fun getOnboardingPageFooterUIState(): OnboardingPageFooterUIState {
    val back = stringResource(Res.string.back_button)
    val next = stringResource(Res.string.next)
    val skip = stringResource(Res.string.skip)
    val getStarted = stringResource(Res.string.lets_get_started)
    return  OnboardingPageFooterUIState(
        next = next,
        back = back,
        getStarted = getStarted,
        skip = skip
    )
}
@Composable
@OptIn(ExperimentalResourceApi::class)
private fun getAListOfOnboardingPageUIStates(
    windowSizeClass: WindowSizeClass
): List<OnboardingPageUIState> {
    val listOfOnboardingPageUIState = mutableListOf<OnboardingPageUIState>()
    val onboardingImages = arrayOf(
        OnboardingImage.appLogo(windowSizeClass),
        OnboardingImage.dailyDigest(windowSizeClass),
        OnboardingImage.audio(windowSizeClass),
        OnboardingImage.bookmark(windowSizeClass),
        OnboardingImage.notification(windowSizeClass),
        OnboardingImage.multiLingual(windowSizeClass)
    )

    val descriptions = stringArrayResource(Res.array.onboarding_description)
    val titles = stringArrayResource(Res.array.onboarding_title)
    val subTitles = mutableListOf(stringResource(Res.string.app_name))
    subTitles.addAll(stringArrayResource(Res.array.onboarding_subtitle))
    for (i in descriptions.indices) {
        val onboardingPageUIState = OnboardingPageUIState(
            title = titles[i],
            subTitle = subTitles[i],
            description = descriptions[i],
            imageRes = onboardingImages[i],
        )
        listOfOnboardingPageUIState.add(onboardingPageUIState)
    }
    return listOfOnboardingPageUIState
}