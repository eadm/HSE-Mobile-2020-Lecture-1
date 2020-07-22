package ru.nobird.android.myapplication.arch.domain.cinema.repository

import io.reactivex.Single
import ru.nobird.android.myapplication.arch.domain.cinema.model.Cinema

interface CinemasRepository {
    fun getCinema(id: Long): Single<Cinema>
}