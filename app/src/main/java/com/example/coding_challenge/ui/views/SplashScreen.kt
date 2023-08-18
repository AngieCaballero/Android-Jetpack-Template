package com.example.coding_challenge.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.coding_challenge.domain.router.AppRouter
import kotlinx.coroutines.delay

@Composable
fun <R: AppRouter> SplashScreen(router: R) {

    LaunchedEffect(key1 = true) {

        delay(2000)
        router.reset()
    }

    Column {
        Text(text = "Hello")

    }
}