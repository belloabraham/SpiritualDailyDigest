package dev.bellab.feature.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.bellab.feature.onboarding.ui.OnboardingPage
import org.cccsharonparish.core.common.helpers.preference.ISettings
import org.cccsharonparish.core.resources.ui.theme.SpiritualDailyDigestTheme
import javax.inject.Inject
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.bellab.core.constants.RequestCode
import dev.bellab.core.ui.PageIndicatorView
import dev.bellab.feature.onboarding.ui.OnboardingUIState
import kotlinx.coroutines.launch
import org.cccsharonparish.core.resources.R
import org.cccsharonparish.core.resources.ui.Image
import org.cccsharonparish.core.resources.ui.Size
import org.cccsharonparish.core.resources.ui.theme.primary50
import org.cccsharonparish.core.resources.ui.theme.primary600


class OnboardingActivity : ComponentActivity() {

    @Inject
    lateinit var settings: ISettings

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val onboardingUIStates by remember {
                mutableStateOf(getAListOfOnboardingUIStates(windowSizeClass))
            }
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = {
                onboardingUIStates.size
            })
            SpiritualDailyDigestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                            beyondBoundsPageCount = pagerState.pageCount - 1,
                        ) { page ->
                            OnboardingPage(
                                currentPage = page,
                                nextPage = page.plus(1),
                                totalPage = pagerState.pageCount,
                                windowSizeClass = windowSizeClass,
                                uiState = onboardingUIStates[page],
                                onSkip = {
                                    closeOnboardingActivity()
                                },
                                onNext = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page.plus(1))
                                    }
                                },
                                onPrev = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page.minus(1))
                                    }
                                },
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(Size.MarginPaddingMedium(windowSizeClass))
                                .align(Alignment.TopEnd),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            for (i in 0 until pagerState.pageCount) {
                                PageIndicatorView(
                                    isSelected = i == pagerState.currentPage,
                                    selectedColor = primary600,
                                    defaultColor = primary50,
                                    defaultRadius = 10.dp,
                                    selectedLength = 20.dp,
                                    animationDurationInMillis = 400,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun closeOnboardingActivity() {
        setResult(RequestCode.ONBOARDING)
        finish()
    }

    private fun getAListOfOnboardingUIStates(windowSizeClass: WindowSizeClass): List<OnboardingUIState> {
        val listOfOnboardingUIState = mutableListOf<OnboardingUIState>()
        val descriptions = resources.getStringArray(R.array.onboarding_description)
        val titles = resources.getStringArray(R.array.onboarding_title)
        val subTitles = resources.getStringArray(R.array.onboarding_subtitle)
        val onboardingImages = intArrayOf(
            Image.onboardingAppLogo(windowSizeClass),
            Image.onboardingAppLogo(windowSizeClass),
            Image.onboardingAudioIcon(windowSizeClass),
            Image.onboardingBookmarkIcon(windowSizeClass),
            Image.onboardingNotificationIcon(windowSizeClass),
            Image.onboardingMultiLingualIcon(windowSizeClass)
        )

        for (i in descriptions.indices) {
            val onboardingUIState = OnboardingUIState(
                title = titles[i],
                subTitle = subTitles[i],
                description = descriptions[i],
                imageRes = onboardingImages[i]
            )
            listOfOnboardingUIState.add(onboardingUIState)
        }
        return listOfOnboardingUIState
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpiritualDailyDigestTheme {
    }
}