package ru.nobird.android.myapplication.data

import io.reactivex.Single
import ru.nobird.android.myapplication.data.NetworkManager
import ru.nobird.android.myapplication.data.cache.ApplicationDatabase
import ru.nobird.android.myapplication.viewmodel.Movie

class MoviesRepository {
    private val moviesDao = ApplicationDatabase.INSTANCE.moviesDao()
    private val networkManager = NetworkManager

    fun getMovies(): Single<List<Movie>> =
        networkManager
            .getItems()
            .doOnSuccess {
                moviesDao.deleteAll()
                moviesDao.insert(it)
            }
            .onErrorResumeNext(getMoviesFromDb())

    private fun getMoviesFromDb(): Single<List<Movie>> =
        Single
            .fromCallable { moviesDao.getMovies() }
}