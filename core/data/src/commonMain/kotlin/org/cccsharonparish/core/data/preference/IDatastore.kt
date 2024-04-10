package org.cccsharonparish.core.data.preference

interface IDatastore {
    suspend fun getLong(key: String, defaultValue: Long): Long

    suspend fun setLong(key: String, value: Long)

    suspend fun getInt(key: String, defaultValue: Int): Int

    suspend fun setInt(key: String, value: Int)

    suspend fun getString(key: String): String?

    suspend fun setString(key: String, value: String)

    suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean

    suspend fun setBoolean(key: String, value: Boolean)
}