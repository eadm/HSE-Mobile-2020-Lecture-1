package ru.nobird.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nobird.android.myapplication.data.NetworkManager
import java.util.concurrent.Executors

class SampleViewModel : ViewModel() {
    private val executor = Executors.newSingleThreadExecutor()

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state


    init {
        _state.value = State(emptyList())
        fetchItems()
    }

    fun onCreateMovie(name: String) {
        val oldState = _state.value ?: return
//        _state.value = oldState.copy(items = oldState.items + Item(oldState.items.size))
    }

    fun onClearItemsClicked() {
        _state.value = State(emptyList())
    }

    private fun fetchItems() {
        executor.execute {
            val items = NetworkManager.getItems()
            _state.postValue(State(items))
        }
    }

    override fun onCleared() {
        executor.shutdown()
    }
}