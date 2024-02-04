package com.example.belajarbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class ListViewAdapter(private val hero:ArrayList<Hero>) : RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {
    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView:ImageView = view.findViewById(R.id.img_item_photo)
        val tvName:TextView = view.findViewById(R.id.tv_item_name)
        val tvDesription:TextView = view.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name,description,photo) = hero[position]
        holder.tvName.text = name
        holder.tvDesription.text= description
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgView)
    }

    override fun getItemCount(): Int = hero.size

}