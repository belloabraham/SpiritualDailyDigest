package org.cccsharonparish.spiritualdailydigest

import App
import android.content.Intent
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
        val appLinkIntent: Intent = intent
        val appLinkData = if (appLinkIntent.data == null) null else appLinkIntent.data.toString()

        setContent {
            App(appLinkData)
        }

    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(null)
}