package com.example.belajarbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListQouteAdapter (private val list:ArrayList<String>) :
    RecyclerView.Adapter<ListQouteAdapter.ListViewHolder>(){

        class ListViewHolder(view:View) : RecyclerView.ViewHolder(view) {
            val tvItem:TextView = view.findViewById(R.id.tvItem)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_quote,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tvItem.text = list[position]
    }
}