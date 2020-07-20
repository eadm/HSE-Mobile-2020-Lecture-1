package ru.nobird.android.myapplication.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.nobird.android.myapplication.viewmodel.Movie

@Database(
    entities = [
        Person::class,
        Movie::class
    ],
    version = ApplicationDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "application_database"

        lateinit var INSTANCE: ApplicationDatabase

        fun init(context: Context) {
            val instance = Room
                .databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    DATABASE_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
        }
    }

    abstract fun personDao(): PersonDao
    abstract fun moviesDao(): MoviesDao
}