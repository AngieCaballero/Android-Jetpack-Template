package com.example.coding_challenge

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.ViewModel
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.domain.router.Coordinator


class App : ViewModel(), AppRouter, Coordinator {

    private var routerIsOn: Boolean = false

    private lateinit var root: Screen
    private lateinit var _currentCoordinator: MutableState<ComposableCoordinator>
    private lateinit var finish: () -> Unit

    /*
    AppRouter
     */

    private fun proceed() {

        if (routerIsOn) { return }

        start()
        routerIsOn = true
    }

    override fun start() {

        root = Screen.SplashScreen
        val routedCoordinator = root.coordinatorFor(this)
        routedCoordinator.start()
        _currentCoordinator = mutableStateOf(routedCoordinator)
    }

    /*
    Router
     */
    override fun pop() {

        val previousCoordinator: ComposableCoordinator = Screen.pop() ?: return finish()
        _currentCoordinator.value = previousCoordinator
    }

    override fun popToRoot() {

        Screen.popToRoot(root)
        _currentCoordinator.value = root.coordinatorFor(this)
    }

    override fun process(route: Screen) {

        _currentCoordinator.value = route.coordinatorFor(this)
        _currentCoordinator.value.start()
    }

    override fun reset(startDestination: Screen) {

        _currentCoordinator.value = startDestination.coordinatorFor(this) // Add the New startDestination Coordinator and start it
        _currentCoordinator.value.start()

        Screen.popToRoot(startDestination)  //Clear your Coordinator Logs
    }
    /*
    =============================================================
     */

    @Composable
    fun MainHost(onFinish: () -> Unit) {

        proceed()
        val routedCoordinator: ComposableCoordinator by remember { _currentCoordinator }
        this.finish = onFinish

        BackHandler { pop() }

        Crossfade(
            targetState = routedCoordinator,
            modifier = Modifier.fillMaxSize(),
            animationSpec = tween(durationMillis = 1000),
            label = "Router_Transition"
        ) { newCoordinator ->
            Screen(state = newCoordinator)
        }
    }
    
    @Composable 
    fun Screen(state: ComposableCoordinator) {
        state.CoordinatedScreen()
    }
}