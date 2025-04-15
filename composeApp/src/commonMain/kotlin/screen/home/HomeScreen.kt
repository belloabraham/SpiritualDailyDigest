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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.text.font.FontStyle
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
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.resources.errorColor
import org.cccsharonparish.core.resources.iconColor
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
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.app_name
import spiritualdailydigest.composeapp.generated.resources.correct_date_time_msg
import spiritualdailydigest.composeapp.generated.resources.download
import spiritualdailydigest.composeapp.generated.resources.download_new_content
import spiritualdailydigest.composeapp.generated.resources.favorite_24px
import spiritualdailydigest.composeapp.generated.resources.key_verse
import spiritualdailydigest.composeapp.generated.resources.later
import spiritualdailydigest.composeapp.generated.resources.message
import spiritualdailydigest.composeapp.generated.resources.reflection
import spiritualdailydigest.composeapp.generated.resources.supplication
import spiritualdailydigest.composeapp.generated.resources.update
import spiritualdailydigest.composeapp.generated.resources.update_content
import spiritualdailydigest.composeapp.generated.resources.update_content_msg

class HomeScreen(private val contentId: String?) : Screen {
    @OptIn(
        ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class,
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
        val navigationItems = getNavigationItems()
        val selectedLanguageCode by homeScreenModel.selectedLanguageCode.collectAsState()
        val scope = rememberCoroutineScope()
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
        var sliderPosition by remember { mutableFloatStateOf(homeScreenModel.getFontSize()) }
        var showUIControls by remember { mutableStateOf(false) }
        val density = LocalDensity.current
        val contentToShare by homeScreenModel.contentToShare.collectAsState()
        val showNextButton by homeScreenModel.showNextButton.collectAsState()
        val showPrevButton by homeScreenModel.showPrevButton.collectAsState()
        val contentByLanguage by homeScreenModel.contentByLanguage.collectAsState()
        val contentSupportedLanguages by homeScreenModel.contentSupportedLanguages.collectAsState()
        val isFavourite by homeScreenModel.isFavourite.collectAsState()
        val bannerUrl by homeScreenModel.bannerUrl.collectAsState()
        val contentUIState by homeScreenModel.contentUIState.collectAsState()
        val contentIsDueForExplicitUpdate by homeScreenModel.contentIsDueForExplicitUpdate.collectAsState()
        val enforceExplicitUpdate by homeScreenModel.enforceExplicitUpdate.collectAsState()
        val resultForEnforcedExplicitUpdate by homeScreenModel.resultForEnforcedExplicitUpdate.collectAsState()


        LaunchedEffect(Unit) {
            homeScreenModel.setUserExitedOnboardingScreen(true)
            delay(500)
            showUIControls = true
            if (contentId != null) {
                homeScreenModel.setSelectedContent(contentId)
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(contentByLanguage?.text?.topic ?: "")
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
                            bannerUrl
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
                        val month = getMonth(contentUIState?.month)
                        Text(
                            "$month-${contentUIState?.day}-${contentUIState?.year}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = (14f + sliderPosition * .1f).sp
                        )

                    }

                    Spacer(Modifier.height(mediumSize))

                    Column(Modifier.padding(horizontal = mediumSize)) {

                        val contentFontSize = sliderPosition
                        val contentLineHeight = contentFontSize * 1.2f
                        val textContent = contentByLanguage?.text
                        val bibleVerse = textContent?.bibleVerse

                        Text(
                            text = bibleVerse?.verses ?: "",
                            fontSize = contentFontSize.sp,
                            fontStyle = FontStyle.Italic,
                            lineHeight = contentLineHeight.sp
                        )
                        Spacer(Modifier.height(smallSize))
                        Text(
                            bibleVerse?.reference ?: "",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = (14f + sliderPosition * .2f).sp
                        )
                        Spacer(Modifier.height(smallSize))
                        Text(
                            stringResource(Res.string.key_verse),
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = (14f + sliderPosition * .2f).sp
                        )
                        Text(
                            text = bibleVerse?.keyVerse ?: "",
                            fontSize = contentFontSize.sp,
                            lineHeight = contentLineHeight.sp
                        )

                        Spacer(Modifier.height(mediumSize))

                        Text(
                            stringResource(Res.string.message),
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = (14f + sliderPosition * .2f).sp
                        )
                        Text(
                            textContent?.message ?: "",
                            fontSize = contentFontSize.sp,
                            lineHeight = contentLineHeight.sp
                        )
                        Spacer(Modifier.height(mediumSize))
                        Text(
                            stringResource(Res.string.supplication),
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = (14f + sliderPosition * .2f).sp
                        )
                        Text(
                            textContent?.supplication ?: "",
                            fontSize = contentFontSize.sp,
                            lineHeight = contentLineHeight.sp
                        )
                        Spacer(Modifier.height(mediumSize))
                        Text(
                            stringResource(Res.string.reflection),
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = (14f + sliderPosition * .2f).sp
                        )
                        Text(
                            textContent?.reflection ?: "",
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
                        if (showPrevButton) {
                            IconButton(onClick = {
                                homeScreenModel.onPrev()
                            }) {
                                Icon(
                                    painter = painterResource(Res.drawable.chevron_left_24px),
                                    contentDescription = "Previous",
                                    tint = iconColor()
                                )
                            }
                        }

                        SwitchIconButton(
                            isChecked = isFavourite,
                            unCheckedIcon = Res.drawable.favorite_24px,
                            checkedIcon = Res.drawable.favorite_fill_24px,
                            unCheckedColor = iconColor(),
                            checkedColor = errorColor()
                        ) {
                            homeScreenModel.toggleFavouriteState(isFavourite)
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

                        if (contentByLanguage?.audioUrl != null) {
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    painter = painterResource(Res.drawable.play_circle_24px),
                                    contentDescription = "Play",
                                    tint = iconColor()
                                )
                            }
                        }

                        if (showNextButton) {
                            IconButton(onClick = {
                                homeScreenModel.onNext()
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
                                contentSupportedLanguages.forEachIndexed { index, language ->
                                    SegmentedButton(
                                        shape = SegmentedButtonDefaults.baseShape,
                                        onClick = {
                                            homeScreenModel.onLanguageSelected(language)
                                        },
                                        selected = language.code == selectedLanguageCode
                                    ) {
                                        Text(language.label ?: "")
                                    }
                                    if (index < contentSupportedLanguages.lastIndex) {
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
                            ListItem(
                                modifier = Modifier.clickable {
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

            if (contentIsDueForExplicitUpdate) {
                ModalBottomSheet(sheetState = bottomSheetState, onDismissRequest = {
                    if(!enforceExplicitUpdate){
                        homeScreenModel.setContentIsDueForExplicitUpdate(false)
                    }
                }) {
                    Column(Modifier.fillMaxWidth().padding(bottom = bottomSheetPaddingBottom())) {
                        val title =
                            if (enforceExplicitUpdate) Res.string.download_new_content else Res.string.update_content
                        val description =
                            if (enforceExplicitUpdate) Res.string.download_new_content else Res.string.update_content_msg
                        val correctDateTimeMsg = stringResource(Res.string.correct_date_time_msg)
                        val appName = stringResource(Res.string.app_name)
                        val positiveText =
                            if (enforceExplicitUpdate) Res.string.download else Res.string.update

                        Text(
                            text = stringResource(title),
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 18.sp
                        )
                        Spacer(Modifier.height(mediumSize))
                        Text(
                            stringResource(description, appName)
                        )
                        Spacer(Modifier.height(smallSize))
                        Text(
                            correctDateTimeMsg,
                        )
                        Spacer(Modifier.height(mediumSize))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = if (!enforceExplicitUpdate) Arrangement.SpaceBetween else Arrangement.End
                        ) {
                            if (!enforceExplicitUpdate) {
                                TextButton(
                                    onClick = {
                                     homeScreenModel.setContentIsDueForExplicitUpdate(false)
                                    }
                                ) {
                                    Text(
                                        text = stringResource(Res.string.later),
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }

                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                ),
                                onClick = {
                                    homeScreenModel.updateDatabaseContentExplicitly()
                                }
                            ) {
                                Text(
                                    text = stringResource(positiveText),
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                        if(resultForEnforcedExplicitUpdate is Result.Loading){
                            LinearProgressIndicator(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                    }
                }
            }
        }
    }
}


@Composable
expect fun ShareButton(text: String)

@Composable
fun ShareIcon() {
    Icon(
        painter = painterResource(Res.drawable.share_outline),
        contentDescription = "Share",
        tint = iconColor()
    )
}