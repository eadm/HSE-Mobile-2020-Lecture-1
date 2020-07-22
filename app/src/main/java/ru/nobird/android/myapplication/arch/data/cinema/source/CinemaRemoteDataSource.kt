package ru.nobird.android.myapplication.arch.data.cinema.source

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.domain.cinema.model.Cinema

interface CinemaRemoteDataSource {
    fun getCinema(cinema: Long): Single<List<Cinema>>
}