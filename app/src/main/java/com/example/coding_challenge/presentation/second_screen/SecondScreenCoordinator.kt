package com.example.coding_challenge.presentation.second_screen

import androidx.compose.runtime.Composable
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator

class SecondScreenCoordinator <R: AppRouter>(
    private var router: R,
    private var name: String
) : ComposableCoordinator, SecondViewCoordinatorRepresentable {

    override fun start() {
        // Initialize your components here!
    }

    @Composable
    override fun CoordinatedScreen() {

    }

    override fun goToNextScreen() {
        TODO("Not yet implemented")
    }

    override fun goBack() {
        router.pop()
    }
}