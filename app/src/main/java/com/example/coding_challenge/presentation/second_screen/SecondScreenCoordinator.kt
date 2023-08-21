package com.example.coding_challenge.presentation.second_screen

import androidx.compose.runtime.Composable
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import com.example.coding_challenge.presentation.home.views.HomeView
import com.example.coding_challenge.presentation.second_screen.views.SecondView

class SecondScreenCoordinator<R : AppRouter>(

    private var router: R,
    private var name: String
) : ComposableCoordinator, SecondViewCoordinatorRepresentable {

    private lateinit var viewModel: SecondViewModel

    override fun start() {

        // Initialize your components here!
        viewModel = SecondViewModel(this)
    }

    @Composable
    override fun CoordinatedScreen() {

        val state = viewModel.state.value

        SecondView(
            state = state,
            onEvent = viewModel::onEvent
        )
    }

    override fun goToNextScreen() {

        TODO("Not yet implemented")
    }

    override fun goBack() {

        router.pop()
    }
}