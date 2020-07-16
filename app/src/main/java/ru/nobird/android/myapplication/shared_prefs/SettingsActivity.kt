package ru.nobird.android.myapplication.shared_prefs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import ru.nobird.android.myapplication.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if (savedInstanceState == null) {
//        supportFragmentManager.commitNow { replace(R.id.containerSettings, SettingsFragmentLayout()) }
            supportFragmentManager.commitNow { replace(R.id.containerSettings, SettingsFragmentDynamic()) }
        }
    }
}