package com.example.firstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listHero = ArrayList<Hero>()

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.setHasFixedSize(true)
        listHero.addAll(getAllList())
        showRecycleList()
    }

    private fun showRecycleList(){
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        val adapter = ListHeroAdapter(listHero) {selectHero ->
            Intent(this@MainActivity,HalamanDetail::class.java).apply {
                putExtra(EXTRA_NAME,selectHero)
                startActivity(this)
            }
        }
        binding.recycleView.adapter = adapter
    }

    private fun getAllList(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()

        for (i in dataName.indices){
            val hero = Hero(dataName[i],dataDescription[i],dataPhoto[i])
            listHero.add(hero)
        }
        return listHero
    }
}