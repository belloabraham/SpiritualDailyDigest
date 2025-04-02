package org.cccsharonparish.core.data.config

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.get
import dev.gitlive.firebase.remoteconfig.remoteConfig
import org.cccsharonparish.core.data.getDebugConfigMinimumFetchInterval
import org.cccsharonparish.core.domain.Config


class RemoteConfig : IRemoteConfig {

    private val remoteConfig = Firebase.remoteConfig

    companion object {
        const val DEFAULT_PLAY_STORE_URL =
            "https://play.google.com/store/apps/details?id=${Config.APP_PACKAGE_NAME}"
        const val DEFAULT_APP_STORE_URL =
            "https://apps.apple.com/ng/app/ccchymns/id${Config.APPLE_STORE_ID}"
        const val DEFAULT_VOLUNTEER_FORM_URL = Config.VOLUNTEER_FORM_URL
        const val DEFAULT_WHATS_APP_FEEDBACK_URL = Config.WHATS_APP_FEEDBACK_URL
    }


    override fun getBoolean(key: String): Boolean {
        return remoteConfig.get<Boolean>(key)
    }

    override fun getLong(key: String): Long {
        return remoteConfig.get<Long>(key)
    }

    override fun getDouble(key: String): Double {
        return remoteConfig.get<Double>(key)
    }

    override fun getString(key: String): String {
        return remoteConfig.get<String>(key)
    }

    override suspend fun initialize(isDebugMode: Boolean) {
        if (isDebugMode) {
            remoteConfig.settings {
                minimumFetchInterval = getDebugConfigMinimumFetchInterval()
            }
        }
        return remoteConfig.setDefaults(
            ConfigKey.APP_STORE_URL to DEFAULT_APP_STORE_URL,
            ConfigKey.PLAY_STORE_URL to DEFAULT_PLAY_STORE_URL,
            ConfigKey.VOLUNTEER_FORM_URL to DEFAULT_VOLUNTEER_FORM_URL,
            ConfigKey.WHATS_APP_FEEDBACK_URL to DEFAULT_WHATS_APP_FEEDBACK_URL,
        )
    }

    override suspend fun fetchAndActive(): Boolean {
        return remoteConfig.fetchAndActivate()
    }

}