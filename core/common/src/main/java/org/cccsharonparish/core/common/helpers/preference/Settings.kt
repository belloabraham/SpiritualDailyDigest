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

class Settings @Inject constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SpiritualDailyDigest")
    }
    suspend fun getLong(key: String, defaultValue: Long): Long {
        val prefKey = longPreferencesKey(key)
        val exampleCounterFlow: Flow<Long> = context.dataStore.data
            .map { preferences ->
                preferences[prefKey] ?: defaultValue
            }
        return withContext(IO) {
            exampleCounterFlow.first()
        }
    }
    suspend fun setLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        withContext(IO) {
            context.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }
    suspend fun getInt(key: String, defaultValue: Int): Int {
        val prefKey = intPreferencesKey(key)
        val exampleCounterFlow: Flow<Int> = context.dataStore.data
            .map { preferences ->
                preferences[prefKey] ?: defaultValue
            }
        return withContext(IO) { exampleCounterFlow.first() }
    }

    suspend fun setInt(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        withContext(IO) {
            context.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }
    suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        val exampleCounterFlow: Flow<String?> = context.dataStore.data
            .map { preferences ->
                preferences[prefKey]
            }
        return withContext(IO) { exampleCounterFlow.first() }
    }
    suspend fun setString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        withContext(IO) {
            context.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }
    suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val prefKey = booleanPreferencesKey(key)
        val exampleCounterFlow: Flow<Boolean?> = context.dataStore.data
            .map { preferences ->
                preferences[prefKey]
            }
        return withContext(IO) { exampleCounterFlow.first() ?: defaultValue }
    }
    suspend fun setBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        withContext(IO) {
            context.dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }
}