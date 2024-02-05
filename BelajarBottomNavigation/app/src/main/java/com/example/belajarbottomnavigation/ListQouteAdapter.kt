package com.example.belajarbottomnavigation

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListQouteAdapter(private val list:ArrayList<String>) :
    RecyclerView.Adapter<ListQouteAdapter.ListViewHolder>(){

        class ListViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val tvItem:TextView = view.findViewById(R.id.tvItem)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}