package org.cccsharonparish.spiritualdailydigest

import App
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import org.cccsharonparish.core.common.google.AppUpdateActivity
import org.cccsharonparish.core.data.config.PreferenceKey
import org.cccsharonparish.core.domain.Config
import org.cccsharonparish.core.common.google.AppReview


class MainActivity : AppUpdateActivity() {
    private lateinit var referrerClient: InstallReferrerClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val appLinkIntent: Intent = intent
        val contentId = if (appLinkIntent.data == null) null else appLinkIntent.data!!.lastPathSegment

        setContent {
            App(contentId)
        }

        initInstallReferrer(this)
    }

    private fun initInstallReferrer(context: Context) {
        referrerClient = InstallReferrerClient.newBuilder(context).build()

        referrerClient.startConnection(object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        val response = referrerClient.installReferrer
                        val contentId = response.installReferrer
                        val sharedPref = getSharedPreferences(Config.PROJECT_NAME, MODE_PRIVATE)
                        with(sharedPref.edit()) {
                            putString(PreferenceKey.CONTENT_ID, contentId)
                            apply()
                        }
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
            }
        })
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App(null)
}