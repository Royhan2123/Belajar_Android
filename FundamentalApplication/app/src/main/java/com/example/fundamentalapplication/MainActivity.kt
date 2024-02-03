package com.example.fundamentalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundamentalapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.topBar.setOnMenuItemClickListener {menuItem ->

            when(menuItem.itemId){
                R.id.menu1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer,HomeFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.menu2 -> {
                    startActivity(Intent(this@MainActivity,MenuActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}