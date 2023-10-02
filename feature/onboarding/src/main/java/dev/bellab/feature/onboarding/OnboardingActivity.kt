package dev.bellab.feature.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.bellab.feature.onboarding.ui.OnboardingPage
import org.cccsharonparish.core.common.helpers.preference.ISettings
import org.cccsharonparish.core.resources.ui.theme.SpiritualDailyDigestTheme
import javax.inject.Inject
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.bellab.feature.onboarding.ui.OnboardingUIState
import org.cccsharonparish.core.resources.R


class OnboardingActivity : ComponentActivity() {

    @Inject
    lateinit var settings: ISettings

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            SpiritualDailyDigestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  OnboardingPage(windowSizeClass = windowSizeClass,
                      uiState = OnboardingUIState(
                      title="Title", subTitle = "Hello", description = "Lorem Ipsum", imageRes = R.drawable.large_notification_icon,
                  ), {}, {}, {})
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpiritualDailyDigestTheme {

    }
}