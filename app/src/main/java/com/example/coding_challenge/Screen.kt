package com.example.coding_challenge

import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.Coordinator
import com.example.coding_challenge.ui.views.home.HomeCoordinator

sealed class Screen(val route: String) {

    object HomeScreen : Screen("home_screen")
    data class DetailScreen(val name: String) : Screen("detail_screen")

    fun coordinatorFor(router: AppRouter): Coordinator {
        return when(this) {
            is HomeScreen -> { HomeCoordinator(router = router) }
            is DetailScreen -> { HomeCoordinator(router = router) }
        }
    }
}
