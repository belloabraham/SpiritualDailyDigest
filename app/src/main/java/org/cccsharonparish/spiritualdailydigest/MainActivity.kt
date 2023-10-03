package org.cccsharonparish.spiritualdailydigest

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.bellab.core.constants.RequestCode
import dev.bellab.feature.onboarding.OnboardingActivity
import kotlinx.coroutines.launch
import org.cccsharonparish.core.common.google.AppUpdateActivity
import org.cccsharonparish.core.common.helpers.preference.ISettings
import org.cccsharonparish.core.common.helpers.preference.Key
import org.cccsharonparish.core.resources.ui.theme.SpiritualDailyDigestTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppUpdateActivity() {

    @Inject
    lateinit var settings: ISettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        lifecycleScope.launch {
            val userIsYetToExitOnboardingPage =
                !settings.getBoolean(Key.USER_EXITED_ONBOARDING, false)
            if (userIsYetToExitOnboardingPage) {
                startActivityForResult(Intent(this@MainActivity, OnboardingActivity::class.java), RequestCode.ONBOARDING)
            }
        }

        setContent {
            SpiritualDailyDigestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RequestCode.ONBOARDING){
            lifecycleScope.launch {
                settings.setBoolean(Key.USER_EXITED_ONBOARDING, true)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpiritualDailyDigestTheme {
    }
}