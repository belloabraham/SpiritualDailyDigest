package screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bottomSheetPaddingBottom
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.resources.errorColor
import org.cccsharonparish.core.resources.iconColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import screen.favourites.FavouritesScreen
import screen.options.MoreOptionsScreen
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.chevron_left_24px
import spiritualdailydigest.composeapp.generated.resources.chevron_right_24px
import spiritualdailydigest.composeapp.generated.resources.expand_all_24px
import spiritualdailydigest.composeapp.generated.resources.favorite_fill_24px
import spiritualdailydigest.composeapp.generated.resources.play_circle_24px
import spiritualdailydigest.composeapp.generated.resources.share_outline
import spiritualdailydigest.composeapp.generated.resources.text_decrease_24px
import spiritualdailydigest.composeapp.generated.resources.text_increase_24px
import org.cccsharonparish.core.ui.AnimatedUIVisibility
import org.cccsharonparish.core.ui.SwitchIconButton
import spiritualdailydigest.composeapp.generated.resources.favorite_24px

class HomeScreen(private val id: String) : Screen {
    @OptIn(
        ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class,
        ExperimentalResourceApi::class
    )
    @Composable
    override fun Content() {
        val homeScreenModel = getScreenModel<HomeScreenModel>()
        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val mediumSize = Size.medium(windowSizeClass)
        val smallSize = Size.small(windowSizeClass)
        val largeSize = Size.large(windowSizeClass)
        var openModalBottomSheet by rememberSaveable { mutableStateOf(false) }
        val bottomSheetState = rememberModalBottomSheetState()
        val languages = getLanguages()
        val navigationItems = getNavigationItems()
        var languageIndex by remember { mutableIntStateOf(homeScreenModel.getLanguageIndex()) }
        val scope = rememberCoroutineScope()
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
        var sliderPosition by remember { mutableFloatStateOf(homeScreenModel.getFontSize()) }
        var showUIControls by remember { mutableStateOf(false) }
        val density = LocalDensity.current
        var isFavourite by remember { mutableStateOf(false) }
        var contentToShare by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            homeScreenModel.setUserExitedOnboardingScreen(true)
            delay(500)
            showUIControls = true
        }

        Scaffold(
            topBar = {

                TopAppBar(
                    title = {
                        Text("Faith")
                    },
                    actions = {
                        AnimatedUIVisibility(
                            showUIControls, density
                        ) {
                            ShareButton(contentToShare)
                        }

                    },
                    scrollBehavior = scrollBehavior,
                )
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            Column(Modifier.padding(it).padding(bottom = mediumSize)) {
                Column(
                    Modifier.weight(1f).verticalScroll(rememberScrollState())
                        .padding(bottom = mediumSize)
                ) {

                    CoilImage(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(4.dp)),
                        imageModel = {
                            "https://firebasestorage.googleapis.com/v0/b/spiritualdailydigest-dev.appspot.com/o/banner-small.png?alt=media&token=66fb525c-2b26-473e-964c-6d409f7dd4ea"
                        },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                                setToSaturation(
                                    0.1f
                                )
                            })
                        ),
                        loading = {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(Modifier.size(largeSize))
                            }
                        }
                    )

                    Spacer(Modifier.height(mediumSize))

                    Column(Modifier.padding(horizontal = mediumSize)) {
                        Text(
                            "May-8-2024",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = (14f + sliderPosition * .1f).sp
                        )

                    }

                    Spacer(Modifier.height(mediumSize))

                    Column(Modifier.padding(horizontal = mediumSize)) {

                        val contentFontSize =   sliderPosition
                        val contentLineHeight = contentFontSize * 1.2f

                        Text(
                            text = "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?",
                            fontSize = contentFontSize.sp,
                            lineHeight = contentLineHeight.sp
                        )
                        Spacer(Modifier.height(smallSize))

                        Text(
                            "Psalm 118:1-8",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            fontSize =  (14f + sliderPosition * .2f).sp
                        )
                        Spacer(Modifier.height(mediumSize))
                        Text(
                            " Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?\n  Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?\n  Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?\n  Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?\n  Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?\n  Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?\n  Lorem ipsum dolor, sit amet consectetur adipisicing elit. Suscipit repellendus eligendi libero tempore dolorum! Atque molestiae quo ex fuga labore. Reprehenderit nihil molestias id pariatur dolore ab iste ex optio?\n",
                            fontSize = contentFontSize.sp,
                            lineHeight = contentLineHeight.sp
                        )
                    }

                }

                AnimatedUIVisibility(
                    showUIControls, density
                ) {
                    HorizontalDivider()
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = mediumSize),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                painter = painterResource(Res.drawable.chevron_left_24px),
                                contentDescription = "Previous",
                                tint = iconColor()
                            )
                        }
                        SwitchIconButton(
                            isChecked = isFavourite,
                            unCheckedIcon = Res.drawable.favorite_24px,
                            checkedIcon = Res.drawable.favorite_fill_24px,
                            unCheckedColor = iconColor(),
                            checkedColor = errorColor()
                        ) {
                            isFavourite = !isFavourite
                        }
                        IconButton(onClick = {
                            openModalBottomSheet = true
                        }) {
                            Icon(
                                painter = painterResource(Res.drawable.expand_all_24px),
                                contentDescription = "More options",
                                tint = iconColor()
                            )
                        }
                        IconButton(onClick = {

                        }) {
                            Icon(
                                painter = painterResource(Res.drawable.play_circle_24px),
                                contentDescription = "Play",
                                tint = iconColor()
                            )
                        }
                        IconButton(onClick = {

                        }) {
                            Icon(
                                painter = painterResource(Res.drawable.chevron_right_24px),
                                contentDescription = "Next",
                                tint = iconColor()
                            )
                        }
                    }
                }


            }

            if (openModalBottomSheet) {
                ModalBottomSheet(sheetState = bottomSheetState, onDismissRequest = {
                    openModalBottomSheet = false
                }) {
                    Column(Modifier.fillMaxWidth().padding(bottom = bottomSheetPaddingBottom())) {
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                                .padding(horizontal = mediumSize)
                        ) {
                            SingleChoiceSegmentedButtonRow {
                                languages.forEachIndexed { index, label ->
                                    SegmentedButton(
                                        shape = SegmentedButtonDefaults.baseShape,
                                        onClick = {
                                            languageIndex = index
                                            homeScreenModel.setLanguageIndex(languageIndex)
                                        },
                                        selected = index == languageIndex
                                    ) {
                                        Text(label)
                                    }
                                    if (index < languages.lastIndex) {
                                        Spacer(modifier = Modifier.width(smallSize))
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(mediumSize))

                        Row(
                            Modifier.padding(horizontal = mediumSize),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.text_decrease_24px),
                                contentDescription = "Text decrease"
                            )
                            Spacer(Modifier.width(smallSize))
                            Slider(
                                modifier = Modifier.weight(1f),
                                value = sliderPosition,
                                valueRange = 20f..100f,
                                onValueChangeFinished = {
                                    homeScreenModel.setFontSize(sliderPosition)
                                },
                                onValueChange = { progress -> sliderPosition = progress }
                            )
                            Spacer(Modifier.width(smallSize))
                            Icon(
                                painter = painterResource(Res.drawable.text_increase_24px),
                                contentDescription = "Text increase"
                            )
                        }


                        navigationItems.forEachIndexed { index, navigation ->
                            ListItem(modifier = Modifier.clickable {
                                val nextScreen = when (index) {
                                    0 -> FavouritesScreen()
                                    1 -> MoreOptionsScreen()
                                    else -> null
                                }
                                scope.launch {
                                    bottomSheetState.hide()
                                    openModalBottomSheet = false
                                }
                                navigator?.push(nextScreen!!)
                            }, leadingContent = {
                                Icon(
                                    painter = painterResource(navigation.leadingIconRes),
                                    contentDescription = navigation.headline,
                                    tint = iconColor()
                                )
                            },
                                headlineContent = {
                                    Text(navigation.headline)
                                },
                                trailingContent = {
                                    Icon(
                                        painter = painterResource(navigation.trailingIconRes),
                                        contentDescription = navigation.headline,
                                        tint = iconColor()
                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}


@Composable
expect fun ShareButton(text: String)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ShareIcon() {
    Icon(
        painter = painterResource(Res.drawable.share_outline),
        contentDescription = "Share",
        tint = iconColor()
    )
}