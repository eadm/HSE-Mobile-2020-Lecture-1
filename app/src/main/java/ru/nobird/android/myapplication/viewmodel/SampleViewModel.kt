package ru.nobird.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import ru.nobird.android.myapplication.data.CinemasRepository
import ru.nobird.android.myapplication.data.MoviesRepository
import ru.nobird.android.myapplication.data.NetworkManager
import kotlin.random.Random

class SampleViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val compositeDisposable = CompositeDisposable()
    private val moviesRepository = MoviesRepository()
    private val cinemasRepository = CinemasRepository()

    init {
        _state.value = State.Idle
        fetchItems()
    }

    fun onCreateMovie(name: String) {
        val item = Movie(id = Random.nextInt(), name = name, cinemaId = 1)
        compositeDisposable += NetworkManager
            .createItem(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { fetchItems() },
                onError = {}
            )
    }

    fun onDeleteLastItem() {
        val lastItem = (state.value as? State.Data)
            ?.movies
            ?.lastOrNull()
            ?: return

        compositeDisposable += NetworkManager
            .deleteItem(lastItem.movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { fetchItems() },
                onError = {}
            )
    }

    fun fetchItems() {
        _state.value = State.Loading
        compositeDisposable += moviesRepository
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
                            MovieData(it, cinemasMap.getValue(it.cinemaId))
                        }
                    }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _state.postValue(State.Data(it))
                },
                onError = { _state.postValue(State.Error) }
            )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}