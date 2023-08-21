package com.example.coding_challenge.presentation.home

import androidx.compose.runtime.Composable
import com.example.coding_challenge.Screen
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.presentation.home.views.HomeView

class HomeScreenCoordinator<R: AppRouter>(

    private val router: R
) : ComposableCoordinator, HomeCoordinatorRepresentable {

    private lateinit var viewModel: HomeViewModel

    override fun start() {

        viewModel = HomeViewModel(this)
    }

    @Composable
    override fun CoordinatedScreen() {

        val state = viewModel.state.value

        HomeView(
            state = state,
            onEvent = viewModel::onEvent
        )
    }

    // Navigation
    override fun didTapBack() {

        router.pop()
    }

    override fun goToNextScreen() {

        router.process(Screen.SecondScreen(msg = "Hello from Home"))
    }
}