package screen.onboarding

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.app_name
import spiritualdailydigest.composeapp.generated.resources.back_button
import spiritualdailydigest.composeapp.generated.resources.lets_get_started
import spiritualdailydigest.composeapp.generated.resources.next
import spiritualdailydigest.composeapp.generated.resources.onboarding_description
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle
import spiritualdailydigest.composeapp.generated.resources.onboarding_title
import spiritualdailydigest.composeapp.generated.resources.skip
import org.cccsharonparish.feature.onboarding.OnboardingPageFooterUIState
import org.cccsharonparish.feature.onboarding.OnboardingPageUIState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalResourceApi::class)
fun getOnboardingPageFooterUIState(): OnboardingPageFooterUIState {
    val back = stringResource(Res.string.back_button)
    val next = stringResource(Res.string.next)
    val skip = stringResource(Res.string.skip)
    val getStarted = stringResource(Res.string.lets_get_started)
    return OnboardingPageFooterUIState(
        next = next,
        back = back,
        getStarted = getStarted,
        skip = skip
    )
}

@Composable
@OptIn(ExperimentalResourceApi::class)
fun getAListOfOnboardingPageUIStates(
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