package com.example.tablayout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsAdapter (activity:AppCompatActivity) : FragmentStateAdapter(activity){

    override fun createFragment(position: Int): Fragment {
    var fragment: Fragment? = null
        when(position){
            0 -> fragment = Home()
            1 -> fragment = Profile()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return  2
    }
}