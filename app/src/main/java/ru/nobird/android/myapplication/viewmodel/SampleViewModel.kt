package ru.nobird.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    init {
        _state.value = State.Idle
        fetchItems()
    }

    fun onCreateMovie(name: String) {
//        val oldState = _state.value ?: return
//        _state.value = oldState.copy(items = oldState.items + Item(oldState.items.size))
    }

    fun onClearItemsClicked() {
        _state.value = State.Data(emptyList())
    }

    fun fetchItems() {
        _state.value = State.Loading

    }

    override fun onCleared() {
    }
}