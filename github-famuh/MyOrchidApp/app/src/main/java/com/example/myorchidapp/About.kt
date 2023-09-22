package com.example.myorchidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.myorchidapp.databinding.ActivityAboutBinding
import com.example.myorchidapp.databinding.ItemDetailBinding

class About : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img = ResourcesCompat.getDrawable(resources, R.drawable.gwe, null)
        binding.imgAbout.setImageDrawable(img)

        binding.nameAbout.text = "Fadhil Muhammad"
        binding.emailAbout.text = "Famuh79@gmail.com"
    }
}