package com.example.firstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapplication.databinding.ActivityHalamanKeduaBinding

class HalamanKedua : AppCompatActivity() {
    private lateinit var binding:ActivityHalamanKeduaBinding
    companion object {
        const val HALAMAN = "halaman"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanKeduaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val halaman = intent.getStringExtra(HALAMAN)

        val text = "Ini Halaman $halaman"

        binding.txtHalaman.text = text
    }
}