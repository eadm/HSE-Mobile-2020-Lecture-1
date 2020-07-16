package ru.nobird.android.myapplication.shared_prefs

import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import ru.nobird.android.myapplication.R

class SettingsFragmentLayout : PreferenceFragmentCompat() {
    companion object {
        private const val ROOT_KEY = "root"
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, ROOT_KEY)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val all = sharedPreferences.all
        all.forEach {
            Log.d("Settings", "Key: ${it.key} Value: ${it.value}")
        }
    }
}