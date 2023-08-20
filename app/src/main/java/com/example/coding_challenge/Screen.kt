package com.example.coding_challenge

import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.presentation.SplashScreenCoordinator
import com.example.coding_challenge.presentation.home.HomeScreenCoordinator
import com.example.coding_challenge.presentation.second_screen.SecondScreenCoordinator

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    data class SecondScreen(val msg: String) : Screen("second_screen")

    companion object {
        private var coordinators = mutableMapOf<String, ComposableCoordinator>()
        private val stack = mutableListOf<String>()

        fun pop(): ComposableCoordinator? {

            val lastRoute = stack.removeLast()
            coordinators.remove(lastRoute)

            if (stack.isEmpty()) return null

            return coordinators[stack.last()]
        }

        fun popToRoot(root: Screen): Boolean {
            val rootCoordinator = coordinators[root.route] ?: return false

            stack.clear()
            coordinators.clear()

            stack.add(root.route)
            coordinators[root.route] = rootCoordinator

            return true
        }
    }

    fun coordinatorFor(router: AppRouter): ComposableCoordinator {
        stack.add(route)
        return coordinators.getOrPut(route) {
            when (this) {
                is SplashScreen -> SplashScreenCoordinator(router)
                is HomeScreen -> HomeScreenCoordinator(router)
                is SecondScreen -> SecondScreenCoordinator(router, msg)
            }
        }
    }
}
