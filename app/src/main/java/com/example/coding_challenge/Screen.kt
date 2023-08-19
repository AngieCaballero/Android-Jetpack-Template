package com.example.coding_challenge

import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.ui.views.home.HomeCoordinator

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    data class DetailScreen(val name: String) : Screen("detail_screen")

    fun coordinatorFor(router: AppRouter): ComposableCoordinator {
        return when(this) {
            is SplashScreen -> { HomeCoordinator(router) }
            is HomeScreen -> { HomeCoordinator(router) }
            is DetailScreen -> { HomeCoordinator(router) }
        }
    }
}
