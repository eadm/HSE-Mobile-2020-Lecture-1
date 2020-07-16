package ru.nobird.android.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(Person::class), version = ApplicationDatabase.DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "application_database"

        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance

            }
            synchronized(this) {
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
                return instance
            }
        }
    }

    abstract fun personDao(): PersonDao
}