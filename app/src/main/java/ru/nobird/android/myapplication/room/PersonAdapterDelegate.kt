package ru.nobird.android.myapplication.room

import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_layout.*
import ru.nobird.android.myapplication.R
import ru.nobird.android.ui.adapterdelegates.AdapterDelegate
import ru.nobird.android.ui.adapterdelegates.DelegateViewHolder

class PersonAdapterDelegate : AdapterDelegate<Person, DelegateViewHolder<Person>>() {
    override fun isForViewType(position: Int, data: Person): Boolean =
        true

    override fun onCreateViewHolder(parent: ViewGroup): DelegateViewHolder<Person> =
        ViewHolder(createView(parent, R.layout.item_layout))

    private class ViewHolder(
        override val containerView: View
    ) : DelegateViewHolder<Person>(containerView),
        LayoutContainer {

        override fun onBind(data: Person) {
            text.text = data.toString()
        }
    }
}