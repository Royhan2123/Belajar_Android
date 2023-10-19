package com.example.tugassubmission.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugassubmission.R
import com.example.tugassubmission.data.model.Story
import com.example.tugassubmission.databinding.ActivityDetailBinding
import com.example.tugassubmission.ext.ConstVal
import com.example.tugassubmission.ext.setImageUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailStoryActivity : AppCompatActivity() {

    private var _activityDetailStoryBinding: ActivityDetailBinding? = null
    private val binding get() = _activityDetailStoryBinding!!

    private lateinit var story: Story

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailStoryBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(_activityDetailStoryBinding?.root)

        initIntent()
        initUI()
    }

    private fun initUI() {
        binding.apply {
            imgStoryThumbnail.setImageUrl(story.photoUrl, true)
            tvStoryTitle.text = story.name
            tvStoryDesc.text = story.description
        }
        title = getString(R.string.title_detail_story)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initIntent() {
        story = intent.getParcelableExtra(ConstVal.BUNDLE_KEY_STORY)!!
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onNavigateUp()
    }

}