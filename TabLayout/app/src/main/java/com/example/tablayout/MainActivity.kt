package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    companion object{
        @StringRes
        private val tab_titles = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val SectionsAdapter = SectionsAdapter(this)
        val viewPager:ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = SectionsAdapter
        val tabs : TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs,viewPager){tab,position -> tab.text =
            resources.getString(tab_titles[position])}.attach()

        supportActionBar?.elevation = 0f
    }
}