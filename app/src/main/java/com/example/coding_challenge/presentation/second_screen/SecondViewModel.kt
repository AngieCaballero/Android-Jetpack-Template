package com.example.coding_challenge.presentation.second_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.coding_challenge.presentation.home.HomeViewEvent
import kotlin.random.Random

sealed class SecondViewEvent {
    object DidTapBack : SecondViewEvent()
    object DidTapChangeBackground : SecondViewEvent()
}

data class SecondViewState(

    var background: Color = Color.Yellow,
    var message: String = ""
)

class SecondViewModel(

    private val coordinator: SecondViewCoordinatorRepresentable
) : ViewModel() {

    private val _state = mutableStateOf(SecondViewState())

    val state get() = _state

    fun onEvent(event: SecondViewEvent) {

        when (event) {

            is SecondViewEvent.DidTapBack -> coordinator.goBack()
            is SecondViewEvent.DidTapChangeBackground -> didTapChangeBackgroundButton()
        }
    }

    private fun didTapChangeBackgroundButton() {

        changeBackgroundColor()
    }

    private fun changeBackgroundColor() {

        _state.value = _state.value.copy(
            background = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat()
            )
        )
    }
}