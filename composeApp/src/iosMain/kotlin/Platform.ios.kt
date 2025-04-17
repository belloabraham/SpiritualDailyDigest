import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.cccsharonparish.core.data.config.ConfigKey
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.Foundation.NSBundle
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSURL
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceOrientation.UIDeviceOrientationLandscapeLeft
import platform.UIKit.UIDeviceOrientation.UIDeviceOrientationLandscapeRight
import platform.UIKit.UIDeviceOrientation.UIDeviceOrientationPortrait
import platform.UIKit.UIDeviceOrientationDidChangeNotification
import platform.UIKit.UIViewController
import platform.UIKit.popoverPresentationController
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.arrow_back_ios_24px
import kotlin.experimental.ExperimentalNativeApi

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

@OptIn(ExperimentalNativeApi::class)
actual fun isDebugMode(): Boolean {
    return Platform.isDebugBinary
}

@Composable
actual fun OrientationChangeListener(
    onOrientationChange: (Orientation) -> Unit,
) {
    var currentOrientation by remember { mutableStateOf(UIDevice.currentDevice.orientation) }

    DisposableEffect(Unit) {
        val observer = NSNotificationCenter.defaultCenter.addObserverForName(
            name = UIDeviceOrientationDidChangeNotification,
            `object` = null,
            queue = NSOperationQueue.mainQueue
        ) {
            val newOrientation = UIDevice.currentDevice.orientation
            if (newOrientation != currentOrientation) {
                currentOrientation = newOrientation

                when (newOrientation) {
                    UIDeviceOrientationPortrait -> onOrientationChange(Orientation.Portrait)
                    UIDeviceOrientationLandscapeLeft,
                    UIDeviceOrientationLandscapeRight -> onOrientationChange(Orientation.Landscape)

                    else -> Unit // Handle other orientations if needed
                }
            }
        }

        // Clean up the observer when the Composable is removed from composition
        onDispose {
            NSNotificationCenter.defaultCenter.removeObserver(observer)
        }
    }
}

actual fun appDownloadUrlConfigKey() = ConfigKey.APP_STORE_URL
