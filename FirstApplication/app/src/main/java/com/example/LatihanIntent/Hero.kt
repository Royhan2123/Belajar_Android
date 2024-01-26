package com.example.LatihanIntent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapplication.databinding.ActivityHeroBinding
import com.example.LatihanIntent.hero.Hero

class Hero : AppCompatActivity() {
    private lateinit var binding:ActivityHeroBinding
    companion object {
        const val USERHERO = "user_hero"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(USERHERO, Hero::class.java)
        }else {
            intent.getParcelableExtra(USERHERO)
        }


        if (hero != null){
            val names = hero.name.toString()
            val addres = hero.addres.toString()
            val city = hero.city.toString()

            binding.txtNameHero.text = names
            binding.txtAddresHero.text = addres
            binding.txtCityHero.text = city
        }
    }
}