package ru.nobird.android.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.nobird.android.myapplication.extension.startActivity
import ru.nobird.android.myapplication.lifecycle.LifecycleExample
import ru.nobird.android.myapplication.viewmodel.ViewModelActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentTopic.setOnClickListener { startActivity<FragmentsActivity>() }
        dialogFragmentTopic.setOnClickListener { startActivity<DialogsActivity>() }
        viewModelTopic.setOnClickListener { startActivity<ViewModelActivity>() }
        lifecycleTopic.setOnClickListener { startActivity<LifecycleExample>() }
    }
}