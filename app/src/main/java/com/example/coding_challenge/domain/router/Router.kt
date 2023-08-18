package com.example.coding_challenge.domain.router

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.example.coding_challenge.Screen

interface Router<Route> {

    fun process(route: Route)
}

interface AppRouter : Router<Screen> {

    fun pop()

    fun popToRoot()

    fun reset(startDestination: Screen = Screen.HomeScreen)

    @SuppressLint("ComposableNaming")
    @Composable
    fun start()
}