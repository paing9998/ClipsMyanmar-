package com.clipsmyanmar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clipsmyanmar.ui.admin.MovieFormScreen
import com.clipsmyanmar.ui.admin.MovieListScreen
import com.clipsmyanmar.ui.auth.SignInScreen
import com.clipsmyanmar.ui.auth.SignUpScreen
import com.clipsmyanmar.ui.dashboard.DashboardScreen
import com.clipsmyanmar.ui.home.HomeScreen
import com.clipsmyanmar.ui.movie_detail.MovieDetailScreen

@Composable
fun AppNavHost(startDestination: String = "signIn") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("signIn") { SignInScreen(navController = navController) }
        composable("signUp") { SignUpScreen(navController = navController) }
        composable("home") { HomeScreen(navController = navController) }
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            MovieDetailScreen(navController = navController, movieId = movieId)
        }
        composable("dashboard") { DashboardScreen(navController = navController) }
        composable("admin/movieList") { MovieListScreen(navController = navController) }
        composable("admin/movieForm") { MovieFormScreen(navController = navController) }
    }
}
