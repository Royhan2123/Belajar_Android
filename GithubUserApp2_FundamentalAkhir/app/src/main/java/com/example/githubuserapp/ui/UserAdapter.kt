package com.example.githubuserapp.ui

import com.example.githubuserapp.R
import com.example.githubuserapp.data.local.entity.UserEntity
import com.example.githubuserapp.databinding.ItemUserBinding


import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.ui.detail.DetailUser

class UserAdapter(private val onFavoriteClick: (UserEntity) -> Unit) :
    ListAdapter<UserEntity, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var isFavorite = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        // favorite
        val icFavView = holder.binding.ivFavorite
        if (user.isFavorite) {
            icFavView.setImageDrawable(ContextCompat.getDrawable(
                icFavView.context,
                R.drawable.ic_favorite_fill
            ))
        } else {
            icFavView.setImageDrawable(ContextCompat.getDrawable(
                icFavView.context,
                R.drawable.ic_favorite_border
            ))
        }


        icFavView.setOnClickListener {
//            user.isFavorite = !user.isFavorite

            onFavoriteClick(user)
            if (user.isFavorite) {
                icFavView.setImageDrawable(ContextCompat.getDrawable(
                    icFavView.context,
                    R.drawable.ic_favorite_fill
                ))
            } else {
                icFavView.setImageDrawable(ContextCompat.getDrawable(
                    icFavView.context,
                    R.drawable.ic_favorite_border
                ))
            }
            Log.d("fav", "user ${user.userLogin} is fav ${user.isFavorite}")
        }

        // ke halaman detail
        holder.binding.cardView.setOnClickListener {

            // kirim data ke detail
            val intent = Intent(it.context, DetailUser::class.java)
            intent.putExtra(DetailUser.EXTRA_USERNAME, user.userLogin)
            holder.itemView.context.startActivity(intent)


        }
    }

    class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(user: UserEntity) {
            binding.itemUserName.text = user.userLogin
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .centerCrop()
                .into(binding.itemUserPhoto)


//            if (user.isFavorite) {
//                binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(
//                    binding.ivFavorite.context,
//                    R.drawable.ic_favorite_fill
//                ))
//            } else {
//                binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(
//                    binding.ivFavorite.context,
//                    R.drawable.ic_favorite_border
//                ))
//            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<UserEntity> =
            object : DiffUtil.ItemCallback<UserEntity>() {
                override fun areItemsTheSame(oldUser: UserEntity, newUser: UserEntity): Boolean {
                    return oldUser.userLogin == newUser.userLogin
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: UserEntity, newUser: UserEntity): Boolean {
                    return oldUser == newUser
                }
            }
    }
}



