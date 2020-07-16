package ru.nobird.android.myapplication.shared_prefs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import ru.nobird.android.myapplication.R

class SharedPreferencesActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "SharedPreferences"

        private const val PREFS_NAME = "Application"
        private const val PREFS_NAME_2 = "Another"

        private const val FLAG = "flag"
        private const val LONG = "long"
        private const val FLOAT = "float"
        private const val SET = "set"
        private const val INT = "int"
        private const val STRING = "string"
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        sharedPreferences.edit(commit = true) {}

        sharedPreferences.edit() {
            putBoolean(FLAG, true)
            putLong(LONG, 12345678L)
            putFloat(FLOAT, 3.14f)
            putStringSet(SET, setOf("D", "B", "C"))
            putInt(INT, 42)
            putString(STRING, "This is a string!")
            apply()
        }

//        // Полная очистка
//        sharedPreferences.edit() {
//            clear()
//            apply()
//        }
//
//        // Удаление по ключу
//        sharedPreferences.edit() {
//            remove(SET)
//            apply()
//        }

        val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        Log.d(TAG, "All: ${defaultSharedPreferences.all}")
    }
}