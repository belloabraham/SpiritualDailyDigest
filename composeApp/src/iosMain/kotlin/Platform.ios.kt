import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.Foundation.NSBundle
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.arrow_back_ios_24px

@OptIn(ExperimentalResourceApi::class)
actual fun getNavigationIcon(): DrawableResource {
    return Res.drawable.arrow_back_ios_24px
}

actual fun getAppVersion(): String {
    return  NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: ""
}