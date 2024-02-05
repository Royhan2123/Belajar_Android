package com.example.belajarbottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.belajarbottomnavigation.databinding.ActivityListQuotesBinding

class ListQuotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListQuotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}