import org.cccsharonparish.spiritualdailydigest.applicationContext
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.arrow_back_24px

@OptIn(ExperimentalResourceApi::class)
actual fun getNavigationIcon(): DrawableResource {
    return Res.drawable.arrow_back_24px
}

actual fun getAppVersion(): String {
    val packageInfo = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
    return packageInfo.versionName
}