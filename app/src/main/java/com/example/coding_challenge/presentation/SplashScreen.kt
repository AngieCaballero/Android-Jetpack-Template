package com.example.coding_challenge.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.coding_challenge.Screen
import com.example.coding_challenge.domain.router.AppRouter
import kotlinx.coroutines.delay

@Composable
fun <R: AppRouter>SplashScreen(router: R) {
    LaunchedEffect(key1 = true) {
        delay(2000) // hold on 4 seconds
        router.reset(Screen.HomeScreen)  //Dismiss
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier
            .background(Color.White),
            text = "Hello")
    }
}