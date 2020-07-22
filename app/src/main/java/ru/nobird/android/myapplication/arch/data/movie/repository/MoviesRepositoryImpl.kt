package ru.nobird.android.myapplication.arch.data.movie.repository

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.data.movie.source.MovieCacheDataSource
import ru.nobird.android.myapplication.arch.data.movie.source.MovieRemoteDataSource
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie
import ru.nobird.android.myapplication.arch.domain.movie.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MoviesRepository {
    override fun getMovies(): Single<List<Movie>> =
        movieRemoteDataSource
            .getMovies()
            .doOnSuccess(movieCacheDataSource::saveMovies)
            .onErrorResumeNext(movieCacheDataSource.getMovies())
}