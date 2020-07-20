package ru.nobird.android.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nobird.android.myapplication.data.NetworkManager
import ru.nobird.android.myapplication.data.cache.ApplicationDatabase
import ru.nobird.android.myapplication.data.cache.PersonDao
import kotlin.random.Random

class SampleViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val personDao: PersonDao = ApplicationDatabase.INSTANCE.personDao()

    init {
        _state.value = State.Idle
        fetchItems()
    }

    fun onCreateMovie(name: String) {
        val item = Item(id = Random.nextInt(), name = name)
        NetworkManager.createItem(item).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                fetchItems()
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {}
        })
    }

    fun onDeleteLastItem() {
        val lastItem = (state.value as? State.Data)
            ?.items
            ?.lastOrNull()
            ?: return

        NetworkManager.deleteItem(lastItem).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                fetchItems()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {}
        })
    }

    fun fetchItems() {
        _state.value = State.Loading
        NetworkManager.getItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                val state =
                    if (response.isSuccessful) {
                    State.Data(response.body() ?: emptyList())
                } else {
                    State.Error
                }
                _state.postValue(state)
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                _state.postValue(State.Error)
            }
        })
    }
}