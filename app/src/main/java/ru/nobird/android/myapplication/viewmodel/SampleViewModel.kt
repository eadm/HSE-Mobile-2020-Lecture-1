package ru.nobird.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    init {
        _state.value = State(emptyList())
    }

    fun onAddItemClicked() {
        val oldState = _state.value ?: return
        _state.value = oldState.copy(items = oldState.items + Item(oldState.items.size))
    }

    fun onClearItemsClicked() {
        _state.value = State(emptyList())
    }
}