package ru.nobird.android.myapplication.arch.domain.movie.repository

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie

interface MoviesRepository {
    fun getMovies(): Single<List<Movie>>
}