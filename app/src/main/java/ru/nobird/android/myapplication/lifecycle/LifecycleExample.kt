package ru.nobird.android.myapplication.lifecycle

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import ru.nobird.android.myapplication.R

internal class ExampleObserver(private val context: Context) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onConnected() {
        Toast.makeText(context, "onConnected", Toast.LENGTH_SHORT).show()
    }
}


class LifecycleExample : AppCompatActivity() {
    private lateinit var observer: ExampleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        observer = ExampleObserver(this)
        lifecycle.addObserver(observer)
    }
}