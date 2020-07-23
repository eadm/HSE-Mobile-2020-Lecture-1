package ru.nobird.android.myapplication.arch.view.movie.ui.adapter

import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.myapplication.arch.domain.movie.model.MovieData
import ru.nobird.android.ui.adapterdelegates.AdapterDelegate
import ru.nobird.android.ui.adapterdelegates.DelegateViewHolder

class MovieAdapterDelegate : AdapterDelegate<MovieData, DelegateViewHolder<MovieData>>() {
    override fun isForViewType(position: Int, data: MovieData): Boolean =
        true

    override fun onCreateViewHolder(parent: ViewGroup): DelegateViewHolder<MovieData> =
        ViewHolder(createView(parent, R.layout.item_movie))

    private class ViewHolder(
        override val containerView: View
    ) : DelegateViewHolder<MovieData>(containerView),
        LayoutContainer {

        override fun onBind(data: MovieData) {
            text.text = "${data.movie.name} / ${data.cinema.name}"
        }
    }
}