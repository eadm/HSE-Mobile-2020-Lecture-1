package ru.nobird.android.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    companion object {
        const val KEY_DELTA = "delta"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        recycler.layoutManager = LinearLayoutManager(this)

        val itemsDelta = listOf(1, 2, 3)
        recycler.adapter =
            Adapter {
                setResult(Activity.RESULT_OK, Intent().putExtra(KEY_DELTA, itemsDelta[it]))
                finish()
            }.apply { items = itemsDelta }
    }
}