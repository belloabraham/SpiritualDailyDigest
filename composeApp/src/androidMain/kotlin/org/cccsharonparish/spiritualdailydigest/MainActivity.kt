package org.cccsharonparish.spiritualdailydigest

import App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.cccsharonparish.core.common.google.AppUpdateActivity

class MainActivity : AppUpdateActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            App()
        }
        // ATTENTION: This was auto-generated to handle app links.
      /*  val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data*/
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}