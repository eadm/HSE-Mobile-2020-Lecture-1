package ru.nobird.android.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.nobird.android.myapplication.extension.startActivity
import ru.nobird.android.myapplication.arch.view.movie.ui.activity.ViewModelActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkTopic.setOnClickListener { startActivity<ViewModelActivity>() }
    }
}