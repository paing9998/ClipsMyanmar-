package com.clipsmyanmar.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.clipsmyanmar.util.LanguageManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private object PreferencesKeys {
        val LANGUAGE_KEY = stringPreferencesKey("language_preference")
    }

    override val language: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.LANGUAGE_KEY] ?: LanguageManager.LANGUAGE_ENGLISH
    }

    override suspend fun setLanguage(lang: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE_KEY] = lang
        }
    }
}
