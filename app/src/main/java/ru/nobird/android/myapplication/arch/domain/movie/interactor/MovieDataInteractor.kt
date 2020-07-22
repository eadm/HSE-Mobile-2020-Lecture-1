package ru.nobird.android.myapplication.arch.domain.movie.interactor

import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import ru.nobird.android.myapplication.arch.domain.cinema.model.Cinema
import ru.nobird.android.myapplication.arch.domain.cinema.repository.CinemasRepository
import ru.nobird.android.myapplication.arch.domain.movie.model.MovieData
import ru.nobird.android.myapplication.arch.domain.movie.repository.MoviesRepository
import javax.inject.Inject

class MovieDataInteractor
@Inject
constructor(
    private val cinemasRepository: CinemasRepository,
    private val moviesRepository: MoviesRepository
) {
    fun getMoviesData(): Single<List<MovieData>> =
        moviesRepository
            .getMovies()
            .flatMap { movies ->
                val cinemaIds = movies
                    .asSequence()
                    .map { it.cinemaId }
                    .toSet()

                cinemaIds
                    .toObservable()
                    .flatMapSingle { cinemasRepository.getCinema(it) }
                    .toList()
                    .map { cinemas ->
                        val cinemasMap: Map<Long, Cinema> = cinemas.associateBy { it.id }
                        movies.map {
                            MovieData(
                                it,
                                cinemasMap.getValue(it.cinemaId)
                            )
                        }
                    }
            }
}