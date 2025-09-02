package com.clipsmyanmar.ui.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen("sign_in_screen")
    object SignUp : Screen("sign_up_screen")
    object Home : Screen("home_screen")
    object MovieDetail : Screen("movie_detail_screen/{movieId}") {
        fun createRoute(movieId: String) = "movie_detail_screen/$movieId"
    }
    object Dashboard : Screen("dashboard_screen")
    object AdminPanel : Screen("admin_panel_screen")
    object MovieList : Screen("admin_movie_list_screen")
    object MovieForm : Screen("admin_movie_form_screen/{movieId}") {
        fun createRoute(movieId: String?) = "admin_movie_form_screen/${movieId ?: "new"}"
    }
}
