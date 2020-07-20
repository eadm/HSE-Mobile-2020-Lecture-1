package ru.nobird.android.myapplication

import android.app.Application
import ru.nobird.android.myapplication.data.cache.ApplicationDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationDatabase.init(this)
    }
}