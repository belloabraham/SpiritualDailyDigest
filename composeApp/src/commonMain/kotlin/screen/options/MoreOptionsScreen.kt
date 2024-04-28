package screen.options

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import getNavigationIcon
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.resources.iconColor
import org.cccsharonparish.core.resources.ratingColorScheme
import org.cccsharonparish.core.ui.Header
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import screen.about.AboutScreen
import screen.notification.NotificationTimeScreen
import screen.web_view.WebViewScreen
import screen.web_view.WebViewUIState
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.about_us
import spiritualdailydigest.composeapp.generated.resources.chat_bubble_24px
import spiritualdailydigest.composeapp.generated.resources.chevron_right_24px
import spiritualdailydigest.composeapp.generated.resources.info_24px
import spiritualdailydigest.composeapp.generated.resources.lock_24px
import spiritualdailydigest.composeapp.generated.resources.notification_time_title
import spiritualdailydigest.composeapp.generated.resources.notifications_active_24px
import spiritualdailydigest.composeapp.generated.resources.privacy_policy
import spiritualdailydigest.composeapp.generated.resources.privacy_url
import spiritualdailydigest.composeapp.generated.resources.rate_app
import spiritualdailydigest.composeapp.generated.resources.set_daily_notification_time
import spiritualdailydigest.composeapp.generated.resources.share_outline
import spiritualdailydigest.composeapp.generated.resources.star_24px
import spiritualdailydigest.composeapp.generated.resources.system_update_24px
import spiritualdailydigest.composeapp.generated.resources.tell_a_friend
import spiritualdailydigest.composeapp.generated.resources.thumb_up_24px
import spiritualdailydigest.composeapp.generated.resources.update_app
import spiritualdailydigest.composeapp.generated.resources.volunteer
import spiritualdailydigest.composeapp.generated.resources.volunteer_activism_24px
import spiritualdailydigest.composeapp.generated.resources.your_feedback

class MoreOptionsScreen:Screen {

    @OptIn(
        ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val sizeMedium = Size.medium(windowSizeClass)
        val navigator = LocalNavigator.current

        Scaffold(
            topBar = {
                Header(navigationIcon = getNavigationIcon()){
                    navigator?.pop()
                }
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(Modifier.padding(it)) {


                Option(
                    Res.drawable.system_update_24px,
                    headline = stringResource(Res.string.update_app)
                ) {

                }

                Option(
                    Res.drawable.notifications_active_24px,
                    headline = stringResource(Res.string.set_daily_notification_time)
                ) {
                    navigator?.push(NotificationTimeScreen())
                }

                Option(
                    Res.drawable.share_outline,
                    headline = stringResource(Res.string.tell_a_friend)
                ) {

                }

                Option(
                    Res.drawable.chat_bubble_24px,
                    headline = stringResource(Res.string.your_feedback)
                ) {

                }

                Option(
                    Res.drawable.volunteer_activism_24px,
                    headline = stringResource(Res.string.volunteer)
                ) {

                }

                Option(
                    Res.drawable.thumb_up_24px,
                    headlineContent = {
                        Row {
                            Text(stringResource(Res.string.rate_app), style = textStyle)
                            Spacer(Modifier.width(sizeMedium))
                            Row {
                                for (i in 1 until 6) {
                                    Icon(
                                        painter = painterResource(Res.drawable.star_24px),
                                        contentDescription = "Rate app",
                                        tint = ratingColorScheme()
                                    )
                                }
                            }
                        }
                    },
                ) {

                }
                val privacy = stringResource(Res.string.privacy_policy)
                val privacyUrl = stringResource(Res.string.privacy_url)

                Option(
                    Res.drawable.lock_24px,
                    headline = privacy
                ) {
                    navigator?.push(
                        WebViewScreen(
                            WebViewUIState(title = privacy, privacyUrl)
                        )
                    )
                }


                Option(Res.drawable.info_24px, headline = stringResource(Res.string.about_us)) {
                    navigator?.push(AboutScreen())
                }
            }
        }

    }
}

val textStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Option(leadingIcon: DrawableResource, headline: String, onClick: () -> Unit) {

    Option(leadingIcon, headlineContent = {
        Text(
            headline,
            style = textStyle,
        )
    }) {
        onClick()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Option(
    leadingIcon: DrawableResource,
    headlineContent: @Composable () -> Unit,
    onClick: () -> Unit
) {

    ListItem(modifier = Modifier.clickable {
        onClick()
    },
        leadingContent = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = iconColor()
            )
        },
        headlineContent = headlineContent,
        trailingContent = {
            Icon(
                painter = painterResource(Res.drawable.chevron_right_24px),
                contentDescription = null,
                tint = iconColor()
            )
        })
}