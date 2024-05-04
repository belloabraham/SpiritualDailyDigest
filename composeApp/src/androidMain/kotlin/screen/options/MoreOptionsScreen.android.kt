package screen.options

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import getAppDownloadUrl
import org.cccsharonparish.core.common.helpers.utils.TextUtil
import org.cccsharonparish.core.resources.iconColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.chevron_right_24px
import spiritualdailydigest.composeapp.generated.resources.promotion
import spiritualdailydigest.composeapp.generated.resources.share_24px
import spiritualdailydigest.composeapp.generated.resources.tell_a_friend


@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun TellAFriend(
) {
    val context = LocalContext.current
    val promotionText = stringResource(Res.string.promotion)+ "\n" +getAppDownloadUrl()

    ListItem(modifier = Modifier.clickable {
        TextUtil.shareText(context, promotionText)
    },
        leadingContent = {
            Icon(
                painter = painterResource(Res.drawable.share_24px),
                contentDescription = "Tell a friend",
                tint = iconColor()
            )
        },
        headlineContent = {
            Text(
                stringResource(Res.string.tell_a_friend),
                style = textStyle,
            )
        },
        trailingContent = {
            Icon(
                painter = painterResource(Res.drawable.chevron_right_24px),
                contentDescription = "Tell a friend",
                tint = iconColor()
            )
        })
}

