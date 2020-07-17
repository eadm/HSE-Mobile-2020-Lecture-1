package ru.nobird.android.myapplication.viewmodel

sealed class State {
    object Idle : State()
    object Loading : State()

    data class Data(
        val items: List<Item>
    ) : State()

    object Error : State()
}
