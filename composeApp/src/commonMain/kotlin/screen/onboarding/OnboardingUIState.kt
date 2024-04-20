package screen.onboarding

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import org.jetbrains.compose.resources.DrawableResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.onboarding_description_1
import spiritualdailydigest.composeapp.generated.resources.onboarding_description_2
import spiritualdailydigest.composeapp.generated.resources.onboarding_description_3
import spiritualdailydigest.composeapp.generated.resources.onboarding_description_4
import spiritualdailydigest.composeapp.generated.resources.onboarding_description_5
import spiritualdailydigest.composeapp.generated.resources.onboarding_description_6
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle_1
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle_2
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle_3
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle_4
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle_5

import spiritualdailydigest.composeapp.generated.resources.onboarding_title_1
import spiritualdailydigest.composeapp.generated.resources.onboarding_title_2
import spiritualdailydigest.composeapp.generated.resources.onboarding_title_3
import spiritualdailydigest.composeapp.generated.resources.onboarding_title_4
import spiritualdailydigest.composeapp.generated.resources.onboarding_title_5
import spiritualdailydigest.composeapp.generated.resources.onboarding_title_6


data class OnboardingPageUIState @OptIn(ExperimentalResourceApi::class) constructor(
    val title:String,
    val subTitle:String,
    val description:String,
    val imageRes: DrawableResource,
): JavaSerializable

@Composable
@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
fun getAListOfOnboardingPageUIStates(
): List<OnboardingPageUIState> {
    val windowSizeClass = calculateWindowSizeClass()
    val listOfOnboardingPageUIState = mutableListOf<OnboardingPageUIState>()
    val onboardingImages = arrayOf(
        OnboardingImage.appLogo(windowSizeClass),
        OnboardingImage.dailyDigest(windowSizeClass),
        OnboardingImage.audio(windowSizeClass),
        OnboardingImage.bookmark(windowSizeClass),
        OnboardingImage.notification(windowSizeClass),
        OnboardingImage.multiLingual(windowSizeClass)
    )

    val descriptions = listOf(
        stringResource(Res.string.onboarding_description_1),
        stringResource(Res.string.onboarding_description_2),
        stringResource(Res.string.onboarding_description_3),
        stringResource(Res.string.onboarding_description_4),
        stringResource(Res.string.onboarding_description_5),
        stringResource(Res.string.onboarding_description_6)
    )
    val titles = listOf(
        stringResource(Res.string.onboarding_title_1),
        stringResource(Res.string.onboarding_title_2),
        stringResource(Res.string.onboarding_title_3),
        stringResource(Res.string.onboarding_title_4),
        stringResource(Res.string.onboarding_title_5),
        stringResource(Res.string.onboarding_title_6)
    )
    val subTitles = listOf(
        stringResource(Res.string.app_name),
        stringResource(Res.string.onboarding_subtitle_1),
        stringResource(Res.string.onboarding_subtitle_2),
        stringResource(Res.string.onboarding_subtitle_3),
        stringResource(Res.string.onboarding_subtitle_4),
        stringResource(Res.string.onboarding_subtitle_5),

        )
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