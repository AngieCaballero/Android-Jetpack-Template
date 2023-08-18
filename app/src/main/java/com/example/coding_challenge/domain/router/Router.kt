package com.example.coding_challenge.domain.router

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.example.coding_challenge.Screen

interface Router<Route> {

    fun pop()

    fun popToRoot()

    fun process(route: Route)
}

interface AppRouter : Router<Screen> {

    @SuppressLint("ComposableNaming")
    @Composable
    fun start()

    fun reset(startDestination: Screen = Screen.HomeScreen)
}