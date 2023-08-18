package com.example.coding_challenge

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.Coordinator
import com.example.coding_challenge.ui.views.SplashScreen

class App(
    private val navController: NavHostController
) : AppRouter {

    private lateinit var root: String
    private lateinit var nextCoordinator: Coordinator

    /*
    AppRouter
     */
    override fun pop() {
        navController.popBackStack()
    }

    override fun popToRoot() {
        navController.popBackStack(root, inclusive = true)
    }

    override fun process(route: Screen) {
        nextCoordinator = route.coordinatorFor(this)
        nextCoordinator.start()
        navController.navigate(route = route.route)
    }

    override fun reset(startDestination: Screen) {
        nextCoordinator = Screen.HomeScreen.coordinatorFor(this)
        nextCoordinator.start()
        navController.navigate(route = startDestination.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    @SuppressLint("ComposableNaming")
    @Composable
    override fun start() {
        root = "splash_screen"
        AppRouterConfiguration()
    }

    /*
    =============================================================
     */

    @Composable
    private fun AppRouterConfiguration() {
        NavHost(navController = navController, startDestination = root) {

            composable(route = "splash_screen") {
                SplashScreen(this@App)
            }
            composable(route = "home_screen") {
                nextCoordinator.CoordinatedView()
            }

            composable(route = "detail_screen" ) {
                nextCoordinator.CoordinatedView()
            }
        }
    }
}
