import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import org.cccsharonparish.core.data.config.PreferenceKey
import platform.Foundation.NSUserDefaults

fun MainViewController() = ComposeUIViewController(
    configure = {
        onFocusBehavior = OnFocusBehavior.DoNothing
    }
) {
    val contentUrl = NSUserDefaults.standardUserDefaults.stringForKey(PreferenceKey.CONTENT_ID)
    App(contentUrl)
}

fun PreferenceKey(): PreferenceKey {
    return PreferenceKey
}