package ru.nobird.android.myapplication.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_view_model.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.myapplication.viewmodel.adapter.ItemAdapterDelegate
import ru.nobird.android.ui.adapters.DefaultDelegateAdapter

class ViewModelActivity : AppCompatActivity() {
    private val viewModel: SampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        addItem.setOnClickListener { viewModel.onAddItemClicked() }
        clear.setOnClickListener { viewModel.onClearItemsClicked() }

        val adapter = DefaultDelegateAdapter<Item>()
        adapter += ItemAdapterDelegate()

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        viewModel.state.observe(this) { state ->
            adapter.items = state.items
        }
    }
}