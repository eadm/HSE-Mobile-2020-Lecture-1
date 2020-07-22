package ru.nobird.android.myapplication.arch.remote.movie.service

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie

interface MovieService {
    @GET("movies")
    fun getMovies(): Single<List<Movie>>

    @POST("movies")
    fun createMovie(@Body movie: Movie): Single<Movie>

    @DELETE("movies/{id}")
    fun deleteMovie(@Path("id") id: Int): Completable
}