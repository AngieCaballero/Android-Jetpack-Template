package com.example.coding_challenge.domain.router

import androidx.compose.runtime.Composable

interface Coordinator {

   fun start()
}

interface ComposableCoordinator : Coordinator {

   @Composable
   fun CoordinatedScreen()
}
