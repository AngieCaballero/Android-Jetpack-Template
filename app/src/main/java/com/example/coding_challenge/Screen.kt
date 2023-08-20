package com.example.coding_challenge

import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.presentation.home.HomeScreenCoordinator

sealed class Screen(val route: String) {
    companion object {
        private var coordinators = mutableMapOf<String, ComposableCoordinator>()
        private val stack = mutableListOf<String>()

        fun pop(): Boolean {
            if (stack.isEmpty()) return false

            val lastRoute = stack.removeLast()
            coordinators.remove(lastRoute)

            return true
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
    object HomeScreen : Screen("home_screen")

    fun coordinatorFor(router: AppRouter): ComposableCoordinator {
        stack.add(route)
        return coordinators.getOrPut(route) {
            when (this) {
                is HomeScreen -> HomeScreenCoordinator(router)
            }
        }
    }
}
