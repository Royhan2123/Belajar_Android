package com.example.myorchidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myorchidapp.databinding.ItemOrchidBinding

class ListOrchidAdapter(private val listOrchid: ArrayList<Orchid>) :
    RecyclerView.Adapter<ListOrchidAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Orchid)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemOrchidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listOrchid.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, desc, spec, img) = listOrchid[position]
        Glide.with(holder.itemView.context)
            .load(img)
            .into(holder.binding.imgItem)
        holder.binding.itemName.text = name
        holder.binding.itemSpec.text = spec

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listOrchid[holder.adapterPosition])
        }

    }

    class ListViewHolder(val binding: ItemOrchidBinding) : RecyclerView.ViewHolder(binding.root)
}