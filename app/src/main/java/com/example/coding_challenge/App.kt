package com.example.coding_challenge

import android.annotation.SuppressLint
import android.window.SplashScreen
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.presentation.SplashScreen


class App : ViewModel(), AppRouter {

    private lateinit var root: Screen
    private lateinit var routedCoordinator: ComposableCoordinator
    private lateinit var finish: () -> Unit
    private lateinit var navController: NavHostController

    /*
    AppRouter
     */
    override fun pop() {
        if (navController.currentBackStack.value.count() == 2) finish()
        Screen.pop()
        navController.popBackStack()
    }

    override fun popToRoot() {
        Screen.popToRoot(root)
        routedCoordinator = root.coordinatorFor(this)
        navController.popBackStack(root.route, inclusive = false)
    }

    override fun process(route: Screen) {
        routedCoordinator = route.coordinatorFor(this)
        routedCoordinator.start()
        navController.navigate(route = route.route)
    }

    override fun reset(startDestination: Screen) {
        routedCoordinator = startDestination.coordinatorFor(this) // Add the New startDestination Coordinator and start it
        routedCoordinator.start()

        Screen.popToRoot(startDestination)  //Clear your CoordinatorLogs

        navController.navigate(route = startDestination.route) { //Navigate and clear the backStack
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }
    /*
    =============================================================
     */

    @Composable
    fun MainHost(onFinish: () -> Unit) {

        this.finish = onFinish
        this.navController = rememberNavController()

        BackHandler { pop() }

        NavHost(navController = navController, startDestination = "splash_screen") {

            composable(route = "splash_screen") {
                SplashScreen(this@App)
            }
            composable(route = "home_screen") {
                routedCoordinator.CoordinatedScreen()

            }
        }
    }
}
