package ru.nobird.android.myapplication.arch.data.cinema.repository

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.data.cinema.source.CinemaRemoteDataSource
import ru.nobird.android.myapplication.arch.domain.cinema.model.Cinema
import ru.nobird.android.myapplication.arch.domain.cinema.repository.CinemasRepository
import javax.inject.Inject

class CinemasRepositoryImpl
@Inject
constructor(
    private val cinemaRemoteDataSource: CinemaRemoteDataSource
) : CinemasRepository {
    override fun getCinema(id: Long): Single<Cinema> =
        cinemaRemoteDataSource
            .getCinema(id)
            .map { it.first() }
}