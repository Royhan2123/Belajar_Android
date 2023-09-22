package com.example.githubuserapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ItemUserBinding

class FollowAdapter(private val context: Context, private val follows: List<FollowItem>) :
    RecyclerView.Adapter<FollowAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() = follows.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val follows = follows[position]
        val username = follows.login

        Glide.with(holder.itemView.context)
            .load(follows.avatarUrl)
            .into(holder.binding.itemUserPhoto)

        holder.binding.itemUserName.text = follows.login

        holder.binding.cardView.setOnClickListener {
            val intent = Intent(context, DetailUser::class.java)

            intent.putExtra(DetailUser.EXTRA_USERNAME, username)

            context.startActivity(intent)
        }
    }
}
