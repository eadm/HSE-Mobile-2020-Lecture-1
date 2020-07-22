package ru.nobird.android.myapplication.arch.cache.movie

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.cache.movie.dao.MoviesDao
import ru.nobird.android.myapplication.arch.data.movie.source.MovieCacheDataSource
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie

class MovieCacheDataSourceImpl
constructor(
    private val moviesDao: MoviesDao
) : MovieCacheDataSource {
    override fun saveMovies(items: List<Movie>) {
        moviesDao.deleteAll()
        moviesDao.insert(items)
    }

    override fun getMovies(): Single<List<Movie>> =
        Single
            .fromCallable { moviesDao.getMovies() }
}