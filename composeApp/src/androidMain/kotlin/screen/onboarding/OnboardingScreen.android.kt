package screen.onboarding

import android.content.Context
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.core.lifecycle.rememberScreenLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import org.cccsharonparish.core.common.helpers.notification.Notification
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.domain.applicationContext
import org.koin.compose.koinInject
import screen.home.HomeScreenModel
import screen.notification.OnboardingNotificationTimeScreen
import screen.permission.getPermissionScreen
import screen.permission.getPermissionUIState

actual fun getOnboardingScreen(contentId: String?): Screen {
    return OnboardingScreen(contentId)
}


class OnboardingScreen(private val contentId: String?) : Screen {
    private lateinit var referrerClient: InstallReferrerClient

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val onboardingNotificationTimeScreen = OnboardingNotificationTimeScreen(contentId)
        val deviceRequiresNotificationPermission =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        val permission = if (deviceRequiresNotificationPermission) Notification.PERMISSION else null
        val permissionUIState by getPermissionUIState(permission)
        val screenModel = getScreenModel<OnboardingScreenModel>()
        initInstallReferrer(LocalContext.current, screenModel)

        OnBoardingPage {
            val nextScreen =
                if (deviceRequiresNotificationPermission && !Notification.isPermissionGranted(
                        applicationContext
                    )
                ) {
                    val permissionScreen = getPermissionScreen(permissionUIState)
                    permissionScreen.nextScreen = onboardingNotificationTimeScreen
                    permissionScreen
                } else {
                    onboardingNotificationTimeScreen
                }
            navigator?.replace(nextScreen)
        }
    }


    private fun initInstallReferrer(context: Context, screenModel:OnboardingScreenModel) {
        referrerClient = InstallReferrerClient.newBuilder(context).build()

        referrerClient.startConnection(object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        val response = referrerClient.installReferrer
                        val contentId = response.installReferrer
                        screenModel.setReferrerContentId(contentId)
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
            }
        })
    }


}

