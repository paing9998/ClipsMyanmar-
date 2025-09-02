package com.clipsmyanmar.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.clipsmyanmar.R

@Composable
fun MovieFormScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.admin_movie_form), style = MaterialTheme.typography.headlineMedium)
        // ... (Form fields for title, description, etc.)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle Gemini generation */ }) {
            Text(stringResource(R.string.generate_with_gemini))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Handle poster upload */ }) {
            Text(stringResource(R.string.upload_poster))
        }
    }
}
