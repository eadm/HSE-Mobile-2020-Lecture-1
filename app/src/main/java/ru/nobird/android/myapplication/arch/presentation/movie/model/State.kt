package ru.nobird.android.myapplication.arch.presentation.movie.model

import ru.nobird.android.myapplication.arch.domain.movie.model.MovieData

sealed class State {
    object Idle : State()
    object Loading : State()

    data class Data(
        val movies: List<MovieData>
    ) : State()

    object Error : State()
}
