package com.example.githubuserapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_USERNAME = "extra_user"
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1, R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        Log.d("Detail", "Username : $username")

        // cek username
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        if (username != null) {
            getDetail(username)
            detailViewModel.findUserFollowings(username)
            detailViewModel.findUserFollowers(username)

            getTotalFolls()
//            Log.d("Detail", "Username dari main adalah : $username")
        } else {
            Log.d("Detail", " : $username tidak ada")
        }

        // layout
        val sectionPagerAdapter = SectionsPagerFollowAdapter(this, bundle)

        val viewPager: ViewPager2 = binding.viewPage
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

    }

    private fun getDetail(username: String) {
        detailViewModel.findDetailUser(username)
        detailViewModel.user.observe(this) { user ->
            setUserData(user)
        }
    }

    private fun getTotalFolls() {
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        detailViewModel.userFollowers.observe(this) { fol ->
            val totalFollower = fol.size
            binding.totalFollower.text = totalFollower.toString()
        }

        detailViewModel.userFollowing.observe(this) { fol ->
            val totalFollowing = fol.size
            binding.totalFollowing.text = totalFollowing.toString()
        }

    }


    private fun setUserData(user: DetailUserResponse) {
        binding.username.text = user.login
        binding.name.text = user.name

        Glide.with(this)
            .load(user.avatarUrl)
            .centerCrop()
            .error(R.drawable.ic_launcher_background)
            .into(binding.circleImageView)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}