package com.example.coding_challenge.presentation.second_screen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coding_challenge.presentation.home.HomeViewState
import com.example.coding_challenge.presentation.second_screen.SecondViewEvent
import com.example.coding_challenge.presentation.second_screen.SecondViewState

@Composable
fun SecondView(
    state: SecondViewState,
    onEvent: (SecondViewEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(state.background),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {

                    onEvent(SecondViewEvent.DidTapBack)
                }) {

                Text(text = "Back")
            }

            Button(
                onClick = {

                    onEvent(SecondViewEvent.DidTapBack)
                }) {

                Text(text = "Next Screen")
            }
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            Arrangement.SpaceBetween
        ) {

            Text(text = state.message)
        }

        Button(
            modifier = Modifier
                .padding(vertical = 100.dp),
            onClick = {

                onEvent(SecondViewEvent.DidTapChangeBackground)
            }) {

            Text(text = "Change Background")
        }
    }
}