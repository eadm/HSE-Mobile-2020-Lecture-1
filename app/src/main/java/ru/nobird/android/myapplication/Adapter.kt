package ru.nobird.android.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class Adapter(
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var items: List<Int> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        private val textView = root.text

        init {
            textView.setOnClickListener { onClick(adapterPosition) }
        }

        fun onBind(text: Int) {
            textView.text = text.toString()
        }
    }
}