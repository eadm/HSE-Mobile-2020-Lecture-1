package ru.nobird.android.myapplication.room

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_room.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.ui.adapters.DefaultDelegateAdapter

class RoomActivity : AppCompatActivity() {
    private val viewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        supportActionBar?.apply {
            title = "Room"
            setDisplayHomeAsUpEnabled(true)
        }

        clear.setOnClickListener { viewModel.deleteAllPeople() }

        val adapter = DefaultDelegateAdapter<Person>()
        adapter += PersonAdapterDelegate()

        recycler.adapter = adapter

        viewModel.people.observe(this) { people ->
            adapter.items = people
        }
    }
}