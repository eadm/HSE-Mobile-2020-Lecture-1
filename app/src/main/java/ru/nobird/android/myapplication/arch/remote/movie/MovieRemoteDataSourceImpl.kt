package ru.nobird.android.myapplication.arch.remote.movie

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.data.movie.source.MovieRemoteDataSource
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie
import ru.nobird.android.myapplication.arch.remote.movie.service.MovieService

class MovieRemoteDataSourceImpl
constructor(
    private val movieService: MovieService
) : MovieRemoteDataSource {
    override fun getMovies(): Single<List<Movie>> =
        movieService.getMovies()
}