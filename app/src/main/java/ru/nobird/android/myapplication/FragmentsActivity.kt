package ru.nobird.android.myapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class FragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)
        supportActionBar?.apply {
            title = getString(R.string.topic_fragment)
            setDisplayHomeAsUpEnabled(true)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
}