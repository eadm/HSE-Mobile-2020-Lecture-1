package ru.nobird.android.myapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * from person_table")
    fun getAllPeople(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(people: List<Person>)

    @Query("DELETE FROM person_table")
    fun deleteAll()

    @Query("SELECT * FROM person_table WHERE firstName=:firstName")
    fun getByName(firstName: String): List<Person>
}