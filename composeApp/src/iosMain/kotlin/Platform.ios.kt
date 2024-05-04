import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import platform.Foundation.NSBundle
import platform.Foundation.NSURL
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.UIKit.popoverPresentationController
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.app_store_url
import spiritualdailydigest.composeapp.generated.resources.arrow_back_ios_24px

@OptIn(ExperimentalResourceApi::class)
actual fun getNavigationIcon(): DrawableResource {
    return Res.drawable.arrow_back_ios_24px
}

fun shareText(text: List<String>, viewController: UIViewController) {
    val activityViewController =
        UIActivityViewController(activityItems = text, applicationActivities = null)
    activityViewController.popoverPresentationController?.sourceView = viewController.view
    viewController.presentViewController(activityViewController, animated = true, completion = null)
}

actual fun bottomSheetPaddingBottom(): Dp {
    return 40.dp
}

actual fun getAppVersion(): String {
    return  NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: ""
}

actual fun openUrl(url: String): Boolean {
    val webUrl = NSURL(string = url)
    if (UIApplication.sharedApplication.canOpenURL(webUrl)) {
        UIApplication.sharedApplication.openURL(webUrl)
        return true
    }
    return false
}

@Composable
@OptIn(ExperimentalResourceApi::class)
actual fun getAppDownloadUrl(): String {
    return stringResource(Res.string.app_store_url)
}
