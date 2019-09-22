package com.example.listapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter(val items : ArrayList<Product>, val context: Context,val listener: (Product) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    //var onItemClick: ((Product) -> Unit)? = null
    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener) //{
       // holder.tvAnimalType.text = items[position].name
    //}
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Product, listener: (Product) -> Unit) = with(itemView) {
        list_text.text = item.name
        setOnClickListener { listener(item) }
    }
}




