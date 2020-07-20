package ru.nobird.android.myapplication.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nobird.android.myapplication.viewmodel.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAll()
}