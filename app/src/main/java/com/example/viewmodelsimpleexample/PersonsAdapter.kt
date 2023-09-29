package com.example.viewmodelsimpleexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonsAdapter(
    private val items: List<Person>,
    private val onItemClicked: (position: Int) -> Unit
) :
    RecyclerView.Adapter<PersonsAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listitem, viewGroup, false)
        return MyViewHolder(view, onItemClicked)
    }
    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewPersonId.text = items[position].id.toString()
        viewHolder.textViewPersonName.text = items[position].name
    }

    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewPersonId: TextView = itemView.findViewById(R.id.list_item_id)
        val textViewPersonName: TextView = itemView.findViewById(R.id.list_item_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition
            // gradle     implementation "androidx.recyclerview:recyclerview:1.2.1"
            onItemClicked(position)
        }
    }
}