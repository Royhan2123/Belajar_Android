package com.example.fundamentalnavigationappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fundamentalnavigationappbar.databinding.ActivityMenuBinding

class menuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener{
                    textView, actionId, event ->
                searchBar.text = searchView.text
                searchView.hide()
                Toast.makeText(this@menuActivity,searchView.text,Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}