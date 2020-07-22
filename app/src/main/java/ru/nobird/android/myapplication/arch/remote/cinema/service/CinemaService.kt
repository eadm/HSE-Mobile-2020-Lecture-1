package ru.nobird.android.myapplication.arch.remote.cinema.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nobird.android.myapplication.arch.domain.cinema.model.Cinema

interface CinemaService {
    @GET("cinemas")
    fun getCinema(@Query("id") cinema: Long): Single<List<Cinema>>
}