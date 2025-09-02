package com.clipsmyanmar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipsmyanmar.ui.navigation.AppNavHost
import com.clipsmyanmar.ui.theme.ClipsMyanmarTheme
import com.clipsmyanmar.util.LanguageManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val language by settingsViewModel.language.collectAsState()

            LanguageManager(language) {
                ClipsMyanmarTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavHost()
                    }
                }
            }
        }
    }
}
