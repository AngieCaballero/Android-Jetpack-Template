package com.example.coding_challenge.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coding_challenge.domain.entity.DogResponse
import com.example.coding_challenge.utils.Constants
import com.example.coding_challenge.utils.Networking
import kotlinx.coroutines.launch
import kotlin.random.Random

sealed class HomeViewEvent {
    object DidTapBack: HomeViewEvent()
    object DidTapNextButton: HomeViewEvent()
    object DidTapChangeBackground: HomeViewEvent()
}
data class HomeViewState(
    var background: Color = Color.White
)

class HomeViewModel(
    private val coordinator: HomeCoordinatorRepresentable
): ViewModel() {

     private val _state = mutableStateOf(HomeViewState())
    val state get() = _state

    fun onEvent(event: HomeViewEvent) {
        when(event) {
            is HomeViewEvent.DidTapNextButton -> didTapNextScreenButton()
            is HomeViewEvent.DidTapChangeBackground -> didTapChangeBackgroundButton()
            is HomeViewEvent.DidTapBack -> coordinator.didTapBack()
        }
    }
    private fun didTapNextScreenButton() {

        coordinator.goToNextScreen()
    }
    private fun didTapChangeBackgroundButton() {
        viewModelScope.launch {

            val result: DogResponse? = Networking.request("breeds/image/random", Constants.BASE_URL, Networking.Companion.HTTPMethod.GET)
            changeBackgroundColor()
        }
    }
    private fun changeBackgroundColor() {

        _state.value = _state.value.copy(background = Color(
            Random.nextFloat(),
            Random.nextFloat(),
            Random.nextFloat()))
    }
}
