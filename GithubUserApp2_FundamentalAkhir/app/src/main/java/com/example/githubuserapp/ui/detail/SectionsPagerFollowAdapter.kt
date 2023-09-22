package com.example.githubuserapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import okhttp3.internal.userAgent

class SectionsPagerFollowAdapter(activity: AppCompatActivity, user: Bundle) : FragmentStateAdapter(activity){
    private var fragmentBundle: Bundle
    init {
        fragmentBundle = user
    }


    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = if (position == 0) FollowFragment() else FollowerFragment()

        Log.d("Section", "position: $position")
        fragment.arguments = this.fragmentBundle
        return fragment
    }


}