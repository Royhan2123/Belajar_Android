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
    private val listener: (Hero) -> Unit
) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(listHero[position])
        holder.itemView.setOnClickListener {
            listener(listHero[position])
        }
    }

    override fun getItemCount(): Int = listHero.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bindView(hero: Hero) {
            Glide.with(itemView.context)
                .load(hero.photo)
                .into(imgPhoto)
            tvName.text = hero.name
            tvDescription.text = hero.description
        }
    }
}
