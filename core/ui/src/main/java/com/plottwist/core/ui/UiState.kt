package com.plottwist.core.ui

sealed class UiState<out T> {
    data object Loading: UiState<Nothing>()
    data class Success<out T>(val value: T) : UiState<T>()
    data object Error: UiState<Nothing>()
}
