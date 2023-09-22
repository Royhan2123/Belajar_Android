package com.example.storyapp.UI.main

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyapp.R
import com.example.storyapp.UI.detail.DetailActivity
import com.example.storyapp.api.response.StoryItem
import com.example.storyapp.databinding.StoryItemBinding


class UserStoryAdapter :
    ListAdapter<StoryItem, UserStoryAdapter.UserStoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserStoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StoryItemBinding.inflate(inflater, parent, false)
        return UserStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserStoryViewHolder, position: Int) {
        val userStory = getItem(position)

        holder.bind(userStory)

        // fade in
        ObjectAnimator.ofFloat(holder.binding.cardView, View.ALPHA, 1f).setDuration(500).start()

        // to detail page
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, userStory.id)
            holder.itemView.context.startActivity(intent)
            Log.d("ADAPTER", "halaman detail ${userStory.name}")
        }

    }

    class UserStoryViewHolder(val binding: StoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userStory: StoryItem) {
            binding.tvItemName.text = userStory.name
            Glide.with(itemView.context)
                .load(userStory.photoUrl)
                .error(R.drawable.ic_baseline_broken_image_24)
                .centerCrop()
                .into(binding.ivItemPhoto)

        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<StoryItem> =
            object : DiffUtil.ItemCallback<StoryItem>() {
                override fun areItemsTheSame(oldUser: StoryItem, newUser: StoryItem): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: StoryItem, newUser: StoryItem): Boolean {
                    return oldUser == newUser
                }
            }
    }
}