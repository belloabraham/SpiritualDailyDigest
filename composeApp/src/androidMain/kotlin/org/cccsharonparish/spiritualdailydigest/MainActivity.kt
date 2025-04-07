package org.cccsharonparish.spiritualdailydigest

import App
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import kotlinx.coroutines.launch
import org.cccsharonparish.core.common.google.AppUpdateActivity
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.koin.android.ext.android.inject

class MainActivity : AppUpdateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val appLinkIntent: Intent = intent
        val contentId = if (appLinkIntent.data == null) null else appLinkIntent.data!!.lastPathSegment

        setContent {
            App(contentId)
        }

    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App(null)
}