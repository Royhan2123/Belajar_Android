package com.example.LatihanIntent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapplication.databinding.ActivityHalamanKeduaBinding

class HalamanKedua : AppCompatActivity() {
    private lateinit var binding:ActivityHalamanKeduaBinding
    companion object {
        const val NAME = "name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanKeduaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(NAME)

        val text = "halaman $name"

        binding.txtHalaman.text = text
    }
}