package org.cccsharonparish.core.data.config

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.get
import dev.gitlive.firebase.remoteconfig.remoteConfig
import org.cccsharonparish.core.data.getDebugConfigMinimumFetchInterval


class RemoteConfig : IRemoteConfig {

    private val remoteConfig = Firebase.remoteConfig


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
        )
    }

    override suspend fun fetchAndActive(): Boolean {
        return remoteConfig.fetchAndActivate()
    }

}