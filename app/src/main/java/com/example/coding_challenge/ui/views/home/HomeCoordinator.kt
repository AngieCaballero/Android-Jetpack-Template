package com.example.coding_challenge.ui.views.home

import androidx.compose.runtime.Composable
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator

class HomeCoordinator<R: AppRouter>(
    private var router: R
) : ComposableCoordinator {

    private lateinit var view: @Composable () -> Unit

    override fun start() {
        // Init your viewModel Here!
        view = { Greeting(name = "I'm a routed View") }
    }

    @Composable
    override fun CoordinatedScreen() {
        view
    }
}