package com.example.LatihanIntent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstapplication.databinding.ActivityMoveWithDataBinding
import com.example.LatihanIntent.person.Person

class MoveWithObjectActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMoveWithDataBinding
    companion object {
        const val  EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveWithDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val person = if (Build.VERSION.SDK_INT >= 33 ){
            intent.getParcelableExtra(EXTRA_PERSON, Person::class.java)
        }else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PERSON)
        }

        if (person != null){
            val text = "Name : ${person.name.toString()},\nEmail : ${person.email}\nAge : ${person.age},\nLocation : ${person.city}"
            binding.tvDataReceived.text = text
        }
    }
}