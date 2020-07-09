package ru.nobird.android.myapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fragments.*
import ru.nobird.android.myapplication.extension.startActivity
import ru.nobird.android.myapplication.fragment.arguments.ArgumentsActivity
import ru.nobird.android.myapplication.fragment.dynamic.FragmentDynamicActivity
import ru.nobird.android.myapplication.fragment.multiple.MultipleFragmentsActivity
import ru.nobird.android.myapplication.fragment.navigation.NavigationActivity

class FragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)
        supportActionBar?.apply {
            title = getString(R.string.topic_fragment)
            setDisplayHomeAsUpEnabled(true)
        }

        dynamicTopic.setOnClickListener { startActivity<FragmentDynamicActivity>() }
        multipleTopic.setOnClickListener { startActivity<MultipleFragmentsActivity>() }
        argumentsTopic.setOnClickListener { startActivity<ArgumentsActivity>() }
        navigationTopic.setOnClickListener { startActivity<NavigationActivity>() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
}