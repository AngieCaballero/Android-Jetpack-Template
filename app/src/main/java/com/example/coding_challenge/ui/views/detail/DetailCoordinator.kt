package com.example.coding_challenge.ui.views.detail

import androidx.compose.runtime.Composable
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.Coordinator

class DetailCoordinator <R: AppRouter>(
    private var router: R,
    private var name: String
) : Coordinator {

    private lateinit var view: @Composable () -> Unit

    override fun start() {
        // Initialize your components here!
    }

    @Composable
    override fun CoordinatedView() {
        view
    }
}