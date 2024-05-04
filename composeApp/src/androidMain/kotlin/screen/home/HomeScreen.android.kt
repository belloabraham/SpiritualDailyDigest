package screen.home

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.cccsharonparish.core.common.helpers.utils.TextUtil

@Composable
actual fun ShareButton(text:String) {
    val context = LocalContext.current
    IconButton(onClick = {
        TextUtil.shareText(context, text = text)
    }) {
        ShareIcon()
    }
}