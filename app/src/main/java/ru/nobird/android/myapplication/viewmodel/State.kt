package ru.nobird.android.myapplication.viewmodel

sealed class State {
    object Idle : State()
    object Loading : State()

    data class Data(
        val movies: List<MovieData>
    ) : State()

    object Error : State()
}
