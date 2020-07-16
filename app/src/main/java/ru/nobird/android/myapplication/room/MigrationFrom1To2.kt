package ru.nobird.android.myapplication.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationFrom1To2 {
    val MIGRATION: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE person_table ADD COLUMN quote TEXT DEFAULT \"This will be a quote!\" NOT NULL")
        }
    }
}