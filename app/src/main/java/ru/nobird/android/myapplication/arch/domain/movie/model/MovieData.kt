package ru.nobird.android.myapplication.arch.domain.movie.model

import ru.nobird.android.myapplication.arch.domain.cinema.model.Cinema
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie

data class MovieData(
    val movie: Movie,
    val cinema: Cinema
)