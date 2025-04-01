package org.cccsharonparish.core.data.config

interface IRemoteConfig {
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long
    fun getDouble(key: String): Double
    fun getString(key: String): String

    suspend fun initialize(isDebugMode: Boolean)

    suspend fun fetchAndActive():Boolean
}