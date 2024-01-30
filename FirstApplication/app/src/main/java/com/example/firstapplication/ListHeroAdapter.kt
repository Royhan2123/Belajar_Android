package com.example.firstapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListHeroAdapter(
    private val listHero: ArrayList<Hero>,
    val listener: (Hero) -> Unit
) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPhoto: ImageView = view.findViewById(R.id.img_item_photo)
        private val txtName: TextView = view.findViewById(R.id.tv_item_name)
        private val txtDescription: TextView = view.findViewById(R.id.tv_item_description)

        fun bindUser(hero: Hero) {
            Glide.with(itemView.context)
                .load(hero.photo)
                .into(imgPhoto)
             txtName.text =  hero.name
            txtDescription.text = hero.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindUser(listHero[position])
        holder.itemView.setOnClickListener {
            listener(listHero[position])
        }
    }
}