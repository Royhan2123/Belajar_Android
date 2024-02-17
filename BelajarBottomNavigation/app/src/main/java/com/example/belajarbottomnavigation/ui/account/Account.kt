package com.example.belajarbottomnavigation.ui.account

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.belajarbottomnavigation.R
import com.example.belajarbottomnavigation.databinding.ActivityAccountBinding

class Account : AppCompatActivity() {
    private lateinit var binding:ActivityAccountBinding
    private lateinit var liveDataViewModel:AccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveDataViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        subscribe()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun subscribe(){
        val elapsedTimeObserver = Observer<Long?> {long ->
            val newText = this@Account.resources.getString(R.string.seconds,long)
            binding.timerTextview.text = newText
        }
        liveDataViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}