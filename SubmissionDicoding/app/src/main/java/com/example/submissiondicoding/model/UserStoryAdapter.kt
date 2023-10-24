package com.example.story.UI.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiondicoding.DetailActivity
import com.example.submissiondicoding.R
import com.example.submissiondicoding.api.response.StoryItem
import com.example.submissiondicoding.databinding.StoryItemBinding

class UserStoryAdapter :
    ListAdapter<StoryItem, UserStoryAdapter.UserStoryViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<StoryItem> =
            object : DiffUtil.ItemCallback<StoryItem>() {
                override fun areItemsTheSame(oldUser: StoryItem, newUser: StoryItem): Boolean {
                    return oldUser.id == newUser.id
                }

                override fun areContentsTheSame(oldUser: StoryItem, newUser: StoryItem): Boolean {
                    return oldUser == newUser
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserStoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StoryItemBinding.inflate(inflater, parent, false)
        return UserStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserStoryViewHolder, position: Int) {
        val userStory = getItem(position)
        holder.bind(userStory)

        // Menambahkan onClickListener untuk navigasi ke DetailActivity
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, userStory.id)
            it.context.startActivity(intent)
        }
    }

    class UserStoryViewHolder(val binding: StoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userStory: StoryItem) {
            // Mengikat data ke tampilan
            binding.itemName.text = userStory.name
            Glide.with(itemView.context)
                .load(userStory.photoUrl)
                .error(R.drawable.baseline_broken_image_24)
                .centerCrop()
                .into(binding.itemPhoto)
        }
    }
}
