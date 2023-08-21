package com.example.coding_challenge.domain.router

import com.example.coding_challenge.Screen

interface Router<Route> {

    fun pop()

    fun popToRoot()

    fun process(route: Route)

    fun reset(startDestination: Screen)
}

interface AppRouter : Router<Screen>