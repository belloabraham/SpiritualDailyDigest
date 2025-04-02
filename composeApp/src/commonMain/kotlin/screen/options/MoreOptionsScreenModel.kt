package screen.options

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import appDownloadUrlConfigKey
import cafe.adriel.voyager.core.model.ScreenModel
import org.cccsharonparish.core.data.config.ConfigKey
import org.cccsharonparish.core.data.config.IRemoteConfig

class MoreOptionsScreenModel(private val remoteConfig: IRemoteConfig) : ScreenModel {
    val downloadUrl by derivedStateOf { getDownloadUrlFromConfig() }
    val feedbackUrl by derivedStateOf { getFeedbackUrlFromConfig() }
    val volunteerFormUrl by derivedStateOf { getVolunteerFormUrlFromConfig() }


    private fun getDownloadUrlFromConfig(): String {
      return remoteConfig.getString(appDownloadUrlConfigKey())
    }

    private fun getFeedbackUrlFromConfig(): String {
        return remoteConfig.getString(ConfigKey.WHATS_APP_FEEDBACK_URL)
    }

    private fun getVolunteerFormUrlFromConfig(): String {
        return remoteConfig.getString(ConfigKey.VOLUNTEER_FORM_URL)
    }
}