package com.clipsmyanmar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clipsmyanmar.ui.screens.admin.AdminPanelScreen
import com.clipsmyanmar.ui.screens.auth.SignInScreen
import com.clipsmyanmar.ui.screens.auth.SignUpScreen
import com.clipsmyanmar.ui.screens.home.HomeScreen
//... import other screens

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(Screen.SignIn.route) { SignInScreen(navController) }
        composable(Screen.SignUp.route) { SignUpScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        // ... other routes
        composable(Screen.AdminPanel.route) { AdminPanelScreen(navController) }
    }
}
