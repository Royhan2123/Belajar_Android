package com.example.firstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.firstapplication.MainActivity.Companion.EXTRA_NAME
import com.example.firstapplication.databinding.ActivityHalamanDetailBinding

class HalamanDetail : AppCompatActivity() {
    private lateinit var binding:ActivityHalamanDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero: Hero? = intent.getParcelableExtra(EXTRA_NAME)

        if (hero != null) {
            binding.txtName.text = hero.name
            binding.txtDescription.text = hero.description
            Glide.with(this)
                .load(hero.photo)
                .into(binding.imgView)
        }
    }
}