package com.example.coding_challenge

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.ui.views.SplashScreen

class App() : ViewModel(), AppRouter {

    private lateinit var navController: NavHostController
    private var root: Screen =  Screen.SplashScreen
    private var routedCoordinator: ComposableCoordinator = root.coordinatorFor(this)

    /*
    AppRouter
     */
    override fun pop() {
        navController.popBackStack()
    }

    override fun popToRoot() {
        navController.popBackStack(root.route, inclusive = false)
    }

    override fun process(route: Screen) {
        routedCoordinator = route.coordinatorFor(this)
        routedCoordinator.start()
        navController.navigate(route = route.route)
    }

    override fun reset(startDestination: Screen) {
        routedCoordinator = Screen.HomeScreen.coordinatorFor(this)
        routedCoordinator.start()
        navController.navigate(route = startDestination.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    /*
    =============================================================
     */

    @Composable
    fun MainHost() {

        this.navController = rememberNavController()

        NavHost(navController = navController, startDestination = root.route) {

            composable(route = "splash_screen") {
                SplashScreen(this@App)
            }
            composable(route = "home_screen") {
                routedCoordinator.CoordinatedScreen()
            }

            composable(route = "detail_screen" ) {
                routedCoordinator.CoordinatedScreen()
            }
        }
    }
}
