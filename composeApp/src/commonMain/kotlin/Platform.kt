import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
expect fun getNavigationIcon(): DrawableResource

expect fun getAppVersion():String