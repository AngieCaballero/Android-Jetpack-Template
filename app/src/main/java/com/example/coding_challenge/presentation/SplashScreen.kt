package com.example.coding_challenge.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.coding_challenge.Screen
import com.example.coding_challenge.domain.router.AppRouter
import com.example.coding_challenge.domain.router.ComposableCoordinator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Put Your Logo Here!")
    }
}

class SplashScreenCoordinator<R: AppRouter> (

    private val router: R
): ComposableCoordinator {

    override fun start() {

        CoroutineScope(Dispatchers.Main).launch {

            delay(2000) // hold on 4
            router.reset(Screen.HomeScreen)  //Dismiss
        }
    }

    @Composable
    override fun CoordinatedScreen() {

        SplashScreen()
    }
}