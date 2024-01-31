package com.example.fundamentalapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundamentalapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val supportFragment = supportFragmentManager
        val homeFragment = HomeFragment()
        val fragment = supportFragment.findFragmentByTag(HomeFragment::class.java.simpleName)

        if (fragment !is HomeFragment){
            supportFragment.beginTransaction()
                .add(R.id.container,homeFragment,HomeFragment::class.java.simpleName)
                .commit()
        }
    }
}