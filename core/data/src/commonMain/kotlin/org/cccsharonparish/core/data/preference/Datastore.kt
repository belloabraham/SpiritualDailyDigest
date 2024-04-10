package org.cccsharonparish.core.data.preference

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okio.Path.Companion.toPath
import org.cccsharonparish.core.data.preference.IDatastore


expect fun dataStorePreferences(
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    migrations: List<DataMigration<Preferences>> = emptyList(),
): DataStore<Preferences>

internal const val SETTINGS_PREFERENCES = "settings_preferences.preferences_pb"

fun createDataStoreWithDefaults(
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    migrations: List<DataMigration<Preferences>> = emptyList(),
    path: () -> String,
) = PreferenceDataStoreFactory
    .createWithPath(
        corruptionHandler = corruptionHandler,
        scope = coroutineScope,
        migrations = migrations,
        produceFile = {
            path().toPath()
        }
    )


class DataStore constructor(
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IDatastore {


    override suspend fun getLong(key: String, defaultValue: Long): Long {
        val prefKey = longPreferencesKey(key)
        val value = dataStore.data.map { preferences ->
            preferences[prefKey]
        }.first()
        return withContext(dispatcher) { value ?: defaultValue }
    }

    override suspend fun setLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }

    override suspend fun getInt(key: String, defaultValue: Int): Int {
        val prefKey = intPreferencesKey(key)
        val value = dataStore.data.map { preferences ->
            preferences[prefKey]
        }.first()
        return withContext(dispatcher) { value ?: defaultValue }
    }

    override suspend fun setInt(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }

    override suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        val value = dataStore.data.map { preferences ->
            preferences[prefKey]
        }.first()
        return withContext(dispatcher) { value }
    }

    override suspend fun setString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }

    override suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val prefKey = booleanPreferencesKey(key)
        val value = dataStore.data.map { preferences ->
            preferences[prefKey]
        }.first()
        return withContext(dispatcher) { value ?: defaultValue }
    }

    override suspend fun setBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        withContext(dispatcher) {
            dataStore.edit { preferences ->
                preferences[prefKey] = value
            }
        }
    }

}