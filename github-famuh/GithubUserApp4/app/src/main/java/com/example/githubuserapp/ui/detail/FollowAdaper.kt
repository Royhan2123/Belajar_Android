package com.example.githubuserapp.ui.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ItemUserFollowsBinding

class FollowAdapter(private val context: Context, private val follows: List<FollowItem>) :
    RecyclerView.Adapter<FollowAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemUserFollowsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserFollowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() = follows.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val follows = follows[position]
        val username = follows.login

        Glide.with(holder.itemView.context)
            .load(follows.avatarUrl)
            .into(holder.binding.itemUserPhotoFollows)

        holder.binding.itemUserNameFollows.text = follows.login

        holder.binding.cardViewFollows.setOnClickListener {
            val intent = Intent(context, DetailUser::class.java)

            intent.putExtra(DetailUser.EXTRA_USERNAME, username)

            context.startActivity(intent)
        }
    }
}
