package com.clipsmyanmar.data.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val language: Flow<String>
    suspend fun setLanguage(lang: String)
}
