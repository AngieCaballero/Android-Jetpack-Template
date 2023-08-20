package com.example.coding_challenge.presentation.second_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlin.random.Random

data class SecondViewState(
    var background: Color = Color.Yellow,
    var message: String = ""
)
class SecondViewModel(
    private val coordinator: SecondViewCoordinatorRepresentable
): ViewModel() {

    private val _state = mutableStateOf(SecondViewState())
    val state get() = _state

    fun onEvent() {

    }
    fun didTapNextScreenButton() {
        coordinator.goToNextScreen()
    }
    fun didTapChangeBackgroundButton() {
        changeBackgroundColor()
    }
    private fun changeBackgroundColor() {
        _state.value = _state.value.copy(background = Color(
            Random.nextFloat(),
            Random.nextFloat(),
            Random.nextFloat())
        )
    }
}