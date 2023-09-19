package com.example.fundamentalasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QouteAdapter(private val listReview:ArrayList<String>):RecyclerView.Adapter<QouteAdapter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_qoute,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: QouteAdapter.ViewHolder, position: Int) {
    holder.tvItem.text = listReview[position]
    }

    override fun getItemCount(): Int {
        return  listReview.size
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvItem:TextView = view.findViewById(R.id.tvItem)
    }
}