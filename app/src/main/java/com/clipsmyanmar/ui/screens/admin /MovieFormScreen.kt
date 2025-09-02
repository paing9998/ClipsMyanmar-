package com.clipsmyanmar.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipsmyanmar.R
import com.clipsmyanmar.util.Resource

@Composable
fun MovieFormScreen(
    viewModel: AdminViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    // ... other state variables for form fields

    val geminiState by viewModel.geminiGenerationState.collectAsState()

    LaunchedEffect(geminiState) {
        if (geminiState is Resource.Success) {
            val data = (geminiState as Resource.Success).data
            data?.let {
                // Update form fields with Gemini data
                // title = it.title // This would be the same
                // rating = it.rating.toString()
                // ... etc.
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Movie Title") }
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.generateMovieDetails(title) }) {
            when (geminiState) {
                is Resource.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                else -> Text("Generate with Gemini ✨")
            }
        }
        
        // ... Rest of the form fields for rating, review, etc.
        // ... Poster upload button
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = { /* TODO: Call viewModel.saveMovie(...) */ }) {
            Text("Save Movie")
        }
    }
}
