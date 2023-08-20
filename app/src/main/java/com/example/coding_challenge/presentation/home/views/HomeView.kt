package com.example.coding_challenge.presentation.home.views

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coding_challenge.presentation.home.HomeViewEvent
import com.example.coding_challenge.presentation.home.HomeViewState
import com.example.coding_challenge.presentation.theme.CodingChallengeTheme

@Composable
fun HomeView(
    state: HomeViewState,
    onEvent: (HomeViewEvent) -> Unit
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
            horizontalArrangement = Arrangement.End
        ) {

            Button(
                onClick = {

                    onEvent(HomeViewEvent.DidTapNextButton)
                }) {

                Text(text = "Next Screen")
            }
        }

        Button(
            modifier = Modifier
                .padding(vertical = 100.dp),
            onClick = {

                onEvent(HomeViewEvent.DidTapChangeBackground)
            }) {

            Text(text = "Change Background")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {

    CodingChallengeTheme {
        HomeView(
            HomeViewState(),
            { }
        )
    }
}