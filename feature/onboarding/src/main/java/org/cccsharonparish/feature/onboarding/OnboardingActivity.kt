package org.cccsharonparish.feature.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.cccsharonparish.core.common.helpers.preference.ISettings
import org.cccsharonparish.core.resources.ui.theme.SpiritualDailyDigestTheme
import javax.inject.Inject
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.bellab.core.constants.RequestCode
import dev.bellab.core.ui.PageIndicatorView
import org.cccsharonparish.feature.onboarding.ui.OnboardingFooter
import org.cccsharonparish.feature.onboarding.ui.OnboardingHeader
import org.cccsharonparish.feature.onboarding.ui.OnboardingImage
import org.cccsharonparish.feature.onboarding.ui.OnboardingUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.cccsharonparish.core.resources.R
import org.cccsharonparish.core.resources.ui.OnboardingImage
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
        val descriptions = resources.getStringArray(R.array.onboarding_description)
        val titles = resources.getStringArray(R.array.onboarding_title)
        val subTitles = resources.getStringArray(R.array.onboarding_subtitle)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val onboardingImageSize = OnboardingImage.size(windowSizeClass)
            val onboardingUIStates by remember {
                derivedStateOf {
                    getAListOfOnboardingUIStates(
                        onboardingImageSize,
                        titles = titles,
                        descriptions = descriptions,
                        subTitles = subTitles
                    )
                }
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
                    OnBoardingPager(
                        onboardingUIStates = onboardingUIStates,
                        pagerState = pagerState,
                        coroutineScope = scope,
                        smallSize = Size.small(windowSizeClass),
                        mediumSize = Size.medium(windowSizeClass),
                        xLargeSize = Size.xLarge(windowSizeClass)
                    ) {
                        closeOnboardingActivity()
                    }
                }
            }
        }
    }

    private fun closeOnboardingActivity() {
        setResult(RequestCode.ONBOARDING)
        finish()
    }

}

private fun getAListOfOnboardingUIStates(
    imageSize: Dp,
    descriptions: Array<String>,
    titles: Array<String>,
    subTitles: Array<String>,
): List<OnboardingUIState> {
    val listOfOnboardingUIState = mutableListOf<OnboardingUIState>()
    val onboardingImages = intArrayOf(
        OnboardingImage.appLogo(imageSize),
        OnboardingImage.dailyDigestIcon(imageSize),
        OnboardingImage.audioIcon(imageSize),
        OnboardingImage.bookmarkIcon(imageSize),
        OnboardingImage.notificationIcon(imageSize),
        OnboardingImage.multiLingualIcon(imageSize)
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPager(
    onboardingUIStates: List<OnboardingUIState>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    smallSize: Dp,
    mediumSize: Dp,
    xLargeSize: Dp,
    onSkip: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondBoundsPageCount = pagerState.pageCount - 1,
        ) { page ->
            val uiState by remember {
                derivedStateOf {
                    onboardingUIStates[page]
                }
            }

            Column(
                Modifier
                    .padding(mediumSize)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                OnboardingHeader(
                    uiState.title,
                    uiState.subTitle,
                    verticalItemSpace = smallSize
                )
                OnboardingImage(
                    uiState.description,
                    uiState.imageRes,
                    verticalPadding = mediumSize,
                    verticalItemSpace = xLargeSize
                )
                OnboardingFooter(
                    currentPage = page,
                    nextPage = page.plus(1),
                    totalPage = pagerState.pageCount,
                    horizontalItemSpace = mediumSize,
                    onSkip = { onSkip() },
                    onNext = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page.plus(1))
                        }
                    },
                    onPrev = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page.minus(1))
                        }
                    },
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(mediumSize)
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

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun OnboardingPagerPreview() {
    SpiritualDailyDigestTheme {
        val titles = stringArrayResource(id = R.array.onboarding_title)
        val subTitles = stringArrayResource(id = R.array.onboarding_subtitle)
        val descriptions = stringArrayResource(id = R.array.onboarding_description)
        val onboardingUIStates by remember {
            mutableStateOf(
                getAListOfOnboardingUIStates(
                    250.dp,
                    titles = titles,
                    subTitles = subTitles,
                    descriptions = descriptions,
                )
            )
        }
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = {
            onboardingUIStates.size
        })
        OnBoardingPager(
            onboardingUIStates = onboardingUIStates,
            pagerState = pagerState,
            coroutineScope = scope,
            smallSize = 8.dp,
            mediumSize = 16.dp,
            xLargeSize = 48.dp
        ) {

        }
    }
}

