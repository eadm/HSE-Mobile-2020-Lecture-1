package ru.nobird.android.myapplication.viewmodel.adapter

import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_layout.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.myapplication.viewmodel.Item
import ru.nobird.android.ui.adapterdelegates.AdapterDelegate
import ru.nobird.android.ui.adapterdelegates.DelegateViewHolder

class ItemAdapterDelegate : AdapterDelegate<Item, DelegateViewHolder<Item>>() {
    override fun isForViewType(position: Int, data: Item): Boolean =
        true

    override fun onCreateViewHolder(parent: ViewGroup): DelegateViewHolder<Item> =
        ViewHolder(createView(parent, R.layout.item_layout))

    private class ViewHolder(
        override val containerView: View
    ) : DelegateViewHolder<Item>(containerView),
        LayoutContainer {

        override fun onBind(data: Item) {
            text.text = data.toString()
        }
    }
}