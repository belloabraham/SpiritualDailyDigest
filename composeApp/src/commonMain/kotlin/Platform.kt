import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
expect fun getNavigationIcon(): DrawableResource
expect fun bottomSheetPaddingBottom(): Dp
expect fun getAppVersion():String
expect fun openUrl(url: String): Boolean
@Composable
expect fun getAppDownloadUrl():String
expect fun isDebugMode():Boolean

@Composable
expect fun OrientationChangeListener(
    onOrientationChange: (Orientation) -> Unit
)

enum class Orientation {
    Portrait,
    Landscape
}