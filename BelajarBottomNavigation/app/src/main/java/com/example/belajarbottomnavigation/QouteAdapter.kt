package com.example.belajarbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QouteAdapter(private val listReview:ArrayList<String>) :
    RecyclerView.Adapter<QouteAdapter.QouteViewHolder>() {

    class QouteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem:TextView = view.findViewById(R.id.tvItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QouteViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_quote,parent,false)
        return QouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QouteViewHolder, position: Int) {
        holder.tvItem.text = listReview[position]
    }

    override fun getItemCount(): Int = listReview.size

}