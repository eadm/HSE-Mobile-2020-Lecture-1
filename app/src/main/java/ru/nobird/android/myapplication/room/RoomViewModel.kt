package ru.nobird.android.myapplication.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class RoomViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "RoomViewModel"
    }
    private var personDao: PersonDao = ApplicationDatabase.getDatabase(application).personDao()

    private val _people = MutableLiveData<List<Person>>()

    val people: LiveData<List<Person>>
        get() = _people

    init {
        AppExecutors.getInstance().diskIo().execute {
            if (personDao.getAllPeople().isEmpty()) {
                personDao.insert(generatePeople())
            }
        }

        AppExecutors.getInstance().diskIo().execute {
            // Достаем всех людей
            val people = personDao.getAllPeople()
            // Достаем по имени
//            val people = personDao.getByName("Tom")
            AppExecutors.getInstance().mainThread().execute {
                _people.value = people
            }
        }
    }


    fun deleteAllPeople() {
        AppExecutors.getInstance().diskIo().execute {
            personDao.deleteAll()
            val people = personDao.getAllPeople()
            AppExecutors.getInstance().mainThread().execute {
                _people.value = people
            }
        }

    }

    // Генерация
    private fun generatePeople(): List<Person> {
        return listOf(
            Person(1, "Tom", "Sawyer" , Date(294742087583)),
            Person(2, "Blark", "Kent" , Date(294842087583)),
            Person(3, "Klark", "Kent" , Date(294242087583)),
            Person(4, "Plark", "Kent" , Date(294142087583)),
            Person(5, "Qlark", "Kent" , Date(294942087583))

        )
    }
}