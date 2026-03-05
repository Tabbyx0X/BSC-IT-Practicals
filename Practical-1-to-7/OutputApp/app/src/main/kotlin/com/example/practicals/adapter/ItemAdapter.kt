package com.example.practicals.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicals.R

/**
 * ItemAdapter – simple RecyclerView adapter for a list of strings.
 *
 * @param items         The list of strings to display.
 * @param onItemClick   Callback invoked with the clicked item's text.
 */
class ItemAdapter(
    private val items: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    /** Holds a reference to the TextView inside each list row. */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the row layout for each item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvItem.text = item
        // Notify the activity/fragment when the row is tapped
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = items.size
}
