package com.example.belajarbottomnavigation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarbottomnavigation.data.response.CustomerReviewsItem
import com.example.belajarbottomnavigation.databinding.ItemReviewBinding

class ReviewAdapter : ListAdapter<CustomerReviewsItem,ReviewAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(val binding:ItemReviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(review:CustomerReviewsItem ){
            binding.tvItem.text = "${review.review}\n- ${review.name}"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ReviewAdapter.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}
