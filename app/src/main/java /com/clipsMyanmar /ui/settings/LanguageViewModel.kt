package com.clipsmyanmar.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clipsmyanmar.util.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val appLanguage = userPreferencesRepository.appLanguage

    fun setAppLanguage(language: String) {
        viewModelScope.launch {
            userPreferencesRepository.setAppLanguage(language)
        }
    }
}
