package ru.nobird.android.myapplication.arch.cache.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.nobird.android.myapplication.arch.cache.base.mapper.Converters
import ru.nobird.android.myapplication.arch.cache.movie.dao.MoviesDao
import ru.nobird.android.myapplication.arch.domain.movie.model.Movie

@Database(
    entities = [
        Movie::class
    ],
    version = ApplicationDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 3
        const val DATABASE_NAME = "application_database"
    }

    abstract fun moviesDao(): MoviesDao
}