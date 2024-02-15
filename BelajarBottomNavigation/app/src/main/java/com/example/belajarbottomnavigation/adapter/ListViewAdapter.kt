package com.example.belajarbottomnavigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.belajarbottomnavigation.Hero
import com.example.belajarbottomnavigation.R

class ListViewAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPhoto: ImageView = view.findViewById(R.id.img_item_photo)
        val tvName:TextView = view.findViewById(R.id.tv_item_name)
        val tvDescription:TextView = view.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero,parent,false)
        return ListViewHolder(view)

    }
    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       val (name,description,photo) = listHero[position]
        holder.tvName.text = name
        holder.tvDescription.text = description

        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
    }
}