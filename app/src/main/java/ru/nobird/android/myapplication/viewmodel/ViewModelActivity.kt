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
import ru.nobird.android.view.base.ui.delegate.ViewStateDelegate
import ru.nobird.android.view.base.ui.extension.showIfNotExists

class ViewModelActivity : AppCompatActivity(), CreateMovieDialogFragment.Callback {
    private val viewModel: SampleViewModel by viewModels()
    private val viewStateDelegate = ViewStateDelegate<State>()
    private val adapter = DefaultDelegateAdapter<MovieData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        supportActionBar?.apply {
            title = getString(R.string.topic_network)
            setDisplayHomeAsUpEnabled(true)
        }

        viewStateDelegate.addState<State.Idle>()
        viewStateDelegate.addState<State.Loading>(progress)
        viewStateDelegate.addState<State.Data>(recycler, addItem, clear)
        viewStateDelegate.addState<State.Error>(errorText, errorAction)

        addItem.setOnClickListener {
            CreateMovieDialogFragment
                .newInstance()
                .showIfNotExists(supportFragmentManager, CreateMovieDialogFragment.TAG)
        }
        clear.setOnClickListener { viewModel.onDeleteLastItem() }
        errorAction.setOnClickListener { viewModel.fetchItems() }

        adapter += ItemAdapterDelegate()

        recycler.adapter = adapter

        viewModel.state.observe(this, ::setState)
    }

    private fun setState(state: State) {
        viewStateDelegate.switchState(state)
        when (state) {
            is State.Data ->
                adapter.items = state.movies
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    override fun onCreateMovie(name: String) {
        viewModel.onCreateMovie(name)
    }
}