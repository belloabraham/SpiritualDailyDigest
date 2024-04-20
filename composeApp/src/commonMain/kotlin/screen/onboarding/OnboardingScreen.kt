package screen.onboarding

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.cccsharonparish.core.resources.primary50
import org.cccsharonparish.core.resources.primary600
import org.cccsharonparish.core.ui.PageIndicatorView
import org.cccsharonparish.core.ui.Size
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi

expect fun getOnboardingScreen(): Screen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPage(
    onSkip: () -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    val onboardingUIStates = getAListOfOnboardingPageUIStates()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        onboardingUIStates.size
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        OnBoardingPager(
            onboardingPageUIStates = onboardingUIStates,
            pagerState = pagerState,
            coroutineScope = scope,
            smallSize = Size.small(windowSizeClass),
            mediumSize = Size.medium(windowSizeClass),
            xLargeSize = Size.xLarge(windowSizeClass)
        ) {
            onSkip()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun OnBoardingPager(
    onboardingPageUIStates: List<OnboardingPageUIState>,
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
                    onboardingPageUIStates[page]
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


