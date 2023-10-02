package org.cccsharonparish.core.common.helpers.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Settings @Inject constructor(private val applicationContext: Context) : ISettings {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SpiritualDailyDigest")
    }
    override suspend fun getLong(key: String, defaultValue: Long): Long {
        val prefKey = longPreferencesKey(key)
        val exampleCounterFlow: Flow<Long> = applicationContext.dataStore.data
            .map { preferences ->
                preferences[prefKey] ?: defaultValue
            }
        return withContext(IO) {
            exampleCounterFlow.first()
        }
    }
    override suspend fun setLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        withContext(IO) {
            applicationContext.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }
    override suspend fun getInt(key: String, defaultValue: Int): Int {
        val prefKey = intPreferencesKey(key)
        val exampleCounterFlow: Flow<Int> = applicationContext.dataStore.data
            .map { preferences ->
                preferences[prefKey] ?: defaultValue
            }
        return withContext(IO) { exampleCounterFlow.first() }
    }

    override suspend fun setInt(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        withContext(IO) {
            applicationContext.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }
    override suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        val exampleCounterFlow: Flow<String?> = applicationContext.dataStore.data
            .map { preferences ->
                preferences[prefKey]
            }
        return withContext(IO) { exampleCounterFlow.first() }
    }
    override suspend fun setString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        withContext(IO) {
            applicationContext.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }
    override suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val prefKey = booleanPreferencesKey(key)
        val exampleCounterFlow: Flow<Boolean?> = applicationContext.dataStore.data
            .map { preferences ->
                preferences[prefKey]
            }
        return withContext(IO) { exampleCounterFlow.first() ?: defaultValue }
    }
    override suspend fun setBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        withContext(IO) {
            applicationContext.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }

}