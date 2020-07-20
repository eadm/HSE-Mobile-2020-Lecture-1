package ru.nobird.android.myapplication.data

import io.reactivex.Single
import ru.nobird.android.myapplication.viewmodel.Cinema

class CinemasRepository {
    private val networkManager = NetworkManager

    fun getCinema(id: Long): Single<Cinema> =
        networkManager
            .getCinema(id)
            .map { it.first() }
}