import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import org.cccsharonparish.core.data.config.ConfigKey
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource


@OptIn(ExperimentalResourceApi::class)
expect fun getNavigationIcon(): DrawableResource
expect fun bottomSheetPaddingBottom(): Dp
expect fun getAppVersion():String
expect fun openUrl(url: String): Boolean
expect fun isDebugMode():Boolean

expect fun appDownloadUrlConfigKey() : String

@Composable
expect fun OrientationChangeListener(
    onOrientationChange: (Orientation) -> Unit
)

enum class Orientation {
    Portrait,
    Landscape
}

expect fun setNotificationTime(hour:Int, minute:Int, isNoon:Boolean)