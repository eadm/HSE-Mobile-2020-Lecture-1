package ru.nobird.android.myapplication.shared_prefs

import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SeekBarPreference
import androidx.preference.SwitchPreferenceCompat

class SettingsFragmentDynamic : PreferenceFragmentCompat() {
    companion object {
        private const val SWITCH_DYNAMIC = "switchDynamic"
        private const val SEEKBAR_DYNAMIC = "seekbarDynamic"
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val screen = preferenceManager.createPreferenceScreen(context)

        val switchOne = SwitchPreferenceCompat(context).apply {
            key = SWITCH_DYNAMIC
            title = "Switch Dynamic"
            summary = "Switch summary"
            setDefaultValue(false)
        }

        val seekBar = SeekBarPreference(context).apply {
            key = SEEKBAR_DYNAMIC
            title = "Seekbar Dynamic"
            max = 100
            setDefaultValue(35)
        }

        screen.addPreference(switchOne)
        screen.addPreference(seekBar)
        preferenceScreen = screen

        // PreferenceManager.getDefaultSharedPreferences(context) - создает и использует файл ${applicationId}_preferences.xml
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        val all = sharedPreferences.all
        all.forEach {
            Log.d("Settings", "Key: ${it.key} Value: ${it.value}")
        }
    }
}