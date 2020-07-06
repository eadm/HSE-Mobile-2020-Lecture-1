package ru.nobird.android.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val KEY_COUNT = "count"
        private const val KEY_DELTA = "delta"

        private const val REQUEST_CODE = 3876
    }

    private var count = 0
    private var countDelta = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        count = savedInstanceState?.getInt(KEY_COUNT) ?: 0
        countDelta = savedInstanceState?.getInt(KEY_DELTA) ?: 1
        textView.text = count.toString()

        button.text = getString(R.string.increment, countDelta)
        button.setOnClickListener {
            count += countDelta
            textView.text = count.toString()
        }

        button2.setOnClickListener {
            startActivityForResult(Intent(this, SecondActivity::class.java), REQUEST_CODE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_COUNT, count)
        outState.putInt(KEY_DELTA, countDelta)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            countDelta = data.getIntExtra(SecondActivity.KEY_DELTA, countDelta)
            button.text = getString(R.string.increment, countDelta)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}