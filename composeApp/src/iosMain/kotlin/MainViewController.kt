import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import platform.Foundation.NSUserDefaults

fun MainViewController() = ComposeUIViewController(
    configure = {
        onFocusBehavior = OnFocusBehavior.DoNothing
    }
) {
    val contentUrl = NSUserDefaults.standardUserDefaults.stringForKey("contentUrl")
    App(contentUrl)
}