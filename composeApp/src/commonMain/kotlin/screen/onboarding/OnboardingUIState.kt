package screen.onboarding

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import org.jetbrains.compose.resources.DrawableResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.app_name
import spiritualdailydigest.composeapp.generated.resources.onboarding_description
import spiritualdailydigest.composeapp.generated.resources.onboarding_subtitle
import spiritualdailydigest.composeapp.generated.resources.onboarding_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource

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