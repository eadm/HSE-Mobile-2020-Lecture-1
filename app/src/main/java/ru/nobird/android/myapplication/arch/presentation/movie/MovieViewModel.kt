package ru.nobird.android.myapplication.arch.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.nobird.android.myapplication.arch.domain.movie.interactor.MovieDataInteractor
import ru.nobird.android.myapplication.arch.presentation.movie.model.State

class MovieViewModel
constructor(
    private val movieDataInteractor: MovieDataInteractor
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val compositeDisposable = CompositeDisposable()

    init {
        _state.value = State.Idle
        fetchItems()
    }

    fun onCreateMovie(name: String) {
        // todo: Сделать через MovieDataInteractor
//        val item = Movie(
//            id = Random.nextInt(),
//            name = name,
//            cinemaId = 1
//        )
//        compositeDisposable += NetworkManager
//            .createItem(item)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onSuccess = { fetchItems() },
//                onError = {}
//            )
    }

    fun onDeleteLastItem() {
        // todo: Сделать через MovieDataInteractor
//        val lastItem = (state.value as? State.Data)
//            ?.movies
//            ?.lastOrNull()
//            ?: return
//
//        compositeDisposable += NetworkManager
//            .deleteItem(lastItem.movie)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onComplete = { fetchItems() },
//                onError = {}
//            )
    }

    fun fetchItems() {
        _state.value = State.Loading
        compositeDisposable += movieDataInteractor
            .getMoviesData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _state.postValue(State.Data(it)) },
                onError = { _state.postValue(State.Error) }
            )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}