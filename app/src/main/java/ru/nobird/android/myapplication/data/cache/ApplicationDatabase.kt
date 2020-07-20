package ru.nobird.android.myapplication.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = ApplicationDatabase.DATABASE_VERSION, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "application_database"

        lateinit var INSTANCE: ApplicationDatabase

        fun init(context: Context) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ApplicationDatabase::class.java,
                DATABASE_NAME
            )
                // Миграция
//                .addMigrations(MigrationFrom1To2.MIGRATION)
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()   <- Так делать не надо!
                .build()
            INSTANCE = instance
        }
    }

    abstract fun personDao(): PersonDao
}