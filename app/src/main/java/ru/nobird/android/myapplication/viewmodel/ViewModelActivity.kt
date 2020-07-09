package ru.nobird.android.myapplication.viewmodel

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_view_model.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.myapplication.viewmodel.adapter.ItemAdapterDelegate
import ru.nobird.android.ui.adapters.DefaultDelegateAdapter

class ViewModelActivity : AppCompatActivity() {
    private val viewModel: SampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        supportActionBar?.apply {
            title = getString(R.string.topic_viewmodel)
            setDisplayHomeAsUpEnabled(true)
        }

        addItem.setOnClickListener { viewModel.onAddItemClicked() }
        clear.setOnClickListener { viewModel.onClearItemsClicked() }

        val adapter = DefaultDelegateAdapter<Item>()
        adapter += ItemAdapterDelegate()

        recycler.adapter = adapter

        viewModel.state.observe(this) { state ->
            adapter.items = state.items
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