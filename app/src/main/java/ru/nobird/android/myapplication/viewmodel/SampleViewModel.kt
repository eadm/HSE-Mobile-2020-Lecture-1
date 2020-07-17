package ru.nobird.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nobird.android.myapplication.data.NetworkManager
import java.lang.Exception
import java.util.concurrent.Executors

class SampleViewModel : ViewModel() {
    private val executor = Executors.newSingleThreadExecutor()

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    init {
        _state.value = State.Idle
        fetchItems()
    }

    fun onCreateMovie(name: String) {
        val oldState = _state.value ?: return
        executor.execute {

        }
//        _state.value = oldState.copy(items = oldState.items + Item(oldState.items.size))
    }

    fun onClearItemsClicked() {
        _state.value = State.Data(emptyList())
    }

    fun fetchItems() {
        _state.value = State.Loading
        executor.execute {
            val state =
                try {
                    Thread.sleep(3000)
                    State.Data(NetworkManager.getItems())
                } catch (_: Exception) {
                    State.Error
                }
            _state.postValue(state)
        }
    }

    override fun onCleared() {
        executor.shutdown()
    }
}