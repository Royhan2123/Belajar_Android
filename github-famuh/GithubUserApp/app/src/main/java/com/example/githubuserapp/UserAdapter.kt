package com.example.githubuserapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ItemUserBinding
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val context: Context, private val listUser: List<User>) :
    RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)

    }

    override fun getItemCount() = listUser.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        val username = listUser[position].login

        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .into(holder.binding.itemUserPhoto)

        holder.binding.itemUserName.text = user.login

        holder.binding.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUser::class.java)
                intent.putExtra(DetailUser.EXTRA_USERNAME, username)
                holder.itemView.context.startActivity(intent)
            }
        }
}


