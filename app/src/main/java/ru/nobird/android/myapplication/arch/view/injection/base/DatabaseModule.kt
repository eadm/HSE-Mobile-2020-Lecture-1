package ru.nobird.android.myapplication.arch.view.injection.base

import android.content.Context
import androidx.room.Room
import ru.nobird.android.myapplication.arch.cache.base.database.ApplicationDatabase

object DatabaseModule {
    fun provideAppDatabase(context: Context): ApplicationDatabase =
        Room
            .databaseBuilder(
                context.applicationContext,
                ApplicationDatabase::class.java,
                ApplicationDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
}