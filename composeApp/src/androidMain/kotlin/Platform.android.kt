import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.cccsharonparish.spiritualdailydigest.applicationContext
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.arrow_back_24px
import spiritualdailydigest.composeapp.generated.resources.play_store_url

@OptIn(ExperimentalResourceApi::class)
actual fun getNavigationIcon(): DrawableResource {
    return Res.drawable.arrow_back_24px
}

actual fun bottomSheetPaddingBottom(): Dp {
    return 16.dp
}

actual fun getAppVersion(): String {
    val packageInfo =
        applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
    return packageInfo.versionName
}

actual fun openUrl(url: String): Boolean {
    val intent = CustomTabsIntent.Builder().build().apply {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    intent.launchUrl(applicationContext, Uri.parse(url))
    return true
}

@Composable
@OptIn(ExperimentalResourceApi::class)
actual fun getAppDownloadUrl(): String {
    return stringResource(Res.string.play_store_url)
}