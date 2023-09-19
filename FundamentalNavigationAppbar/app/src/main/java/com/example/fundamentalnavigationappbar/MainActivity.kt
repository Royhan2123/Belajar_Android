package com.example.fundamentalnavigationappbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundamentalnavigationappbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setOnMenuItemClickListener{
           menu -> when(menu.itemId){
               R.id.menu1 -> {
                   supportFragmentManager.beginTransaction().replace(
                       R.id.fragment_container,menuFragment()).addToBackStack(null).commit()
                   true
               }
            R.id.menu2 -> {
                val intent = Intent(this,menuActivity::class.java)
                startActivity(intent)
                true
            }else -> false
            }
        }
    }
}