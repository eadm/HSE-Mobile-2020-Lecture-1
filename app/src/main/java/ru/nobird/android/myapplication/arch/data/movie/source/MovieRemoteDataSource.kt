package ru.nobird.android.myapplication.arch.data.movie.source

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie

interface MovieRemoteDataSource {
    fun getMovies(): Single<List<Movie>>
}