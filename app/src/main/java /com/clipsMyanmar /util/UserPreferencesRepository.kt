package com.clipsmyanmar.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class UserPreferencesRepository @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val APP_LANGUAGE = stringPreferencesKey("app_language")
    }

    val appLanguage: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.APP_LANGUAGE] ?: "en"
        }

    suspend fun setAppLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_LANGUAGE] = language
        }
    }
}
