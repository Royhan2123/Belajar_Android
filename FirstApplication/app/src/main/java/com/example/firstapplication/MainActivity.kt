package com.example.firstapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.LatihanReycleview.ListHeroAdapter
import com.example.firstapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listHero  = ArrayList<Hero> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.setHasFixedSize(true)
        listHero.addAll(getListHeroes())
        showRecycleList()
    }

    private fun showRecycleList(){
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(listHero)
        binding.recycleView.adapter = listHeroAdapter
    }

    @SuppressLint("Recycle")
    private fun getListHeroes() : ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()

        for (i in dataName.indices){
            val hero = Hero(dataName[i],dataDescription[i],dataPhoto.getResourceId(i,-1))
            listHero.add(hero)
        }

        return listHero
    }

}