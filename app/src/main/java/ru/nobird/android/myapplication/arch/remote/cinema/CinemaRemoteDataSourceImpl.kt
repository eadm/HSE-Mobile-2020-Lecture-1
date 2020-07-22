package ru.nobird.android.myapplication.arch.remote.cinema

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.data.cinema.source.CinemaRemoteDataSource
import ru.nobird.android.myapplication.arch.domain.cinema.model.Cinema
import ru.nobird.android.myapplication.arch.remote.cinema.service.CinemaService

class CinemaRemoteDataSourceImpl
constructor(
    private val cinemaService: CinemaService
) : CinemaRemoteDataSource {
    override fun getCinema(cinema: Long): Single<List<Cinema>> =
        cinemaService.getCinema(cinema)
}