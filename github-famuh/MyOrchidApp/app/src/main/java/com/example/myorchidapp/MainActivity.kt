package com.example.myorchidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvOrchids: RecyclerView
    private val list = ArrayList<Orchid>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvOrchids = findViewById(R.id.rv_orchids)
        rvOrchids.setHasFixedSize(true)

        list.addAll(getListOrchids())
        showRecyclerList()
    }

    private fun getListOrchids(): ArrayList<Orchid> {
        val dataName = resources.getStringArray(R.array.orchid_name)
        val dataDescription = resources.getStringArray(R.array.orchid_desc)
        val dataImg = resources.getStringArray(R.array.orcid_img)
        val dataSpecies = resources.getStringArray(R.array.orchid_species)
        val listOrchid = ArrayList<Orchid>()

        for (i in dataName.indices) {
            val orchid = Orchid(dataName[i], dataDescription[i], dataSpecies[i], dataImg[i])
            listOrchid.add(orchid)
        }
        return listOrchid
    }

    private fun showRecyclerList() {
        rvOrchids.layoutManager = LinearLayoutManager(this)
        val listOrchidAdapter = ListOrchidAdapter(list)
        rvOrchids.adapter = listOrchidAdapter

        listOrchidAdapter.setOnItemClickCallback(object : ListOrchidAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Orchid) {
                val orchid = Orchid(
                    data.name,
                    data.description,
                    data.species,

                    data.photo
                )
                val moveWithObjectIntent = Intent(this@MainActivity, OrchidDetail::class.java)
                moveWithObjectIntent.putExtra(OrchidDetail.EXTRA_DATA, orchid)
//                Toast.makeText(this@MainActivity, "Kamu memilih" + data.name, Toast.LENGTH_SHORT).show()
                startActivity(moveWithObjectIntent)
            }
        })
    }

    // Action Appbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveToAbout = Intent(this, About::class.java)
                startActivity(moveToAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}