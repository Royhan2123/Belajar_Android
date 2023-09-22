package com.example.mytablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    // tes kirim data dari activity ke fragment
    var appName: String = ""

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

//        var fragment: Fragment? = null
//        when(position){
//            0 -> fragment = HomeFragment()
//            1 -> fragment = ProfileFragment()
//        }
//        return fragment as Fragment

        val fragment = HomeFragment()

        // mengirim data antar fragment
        fragment.arguments = Bundle().apply {
            putInt(HomeFragment.ARG_SECTION_NUMBER, position + 1)

            // kirim data activity ke fragment
            putString(HomeFragment.ARG_NAME, appName)
        }
        return fragment
    }
}