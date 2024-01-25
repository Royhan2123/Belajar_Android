package com.example.firstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firstapplication.databinding.ActivityMainBinding
import com.example.hero.Hero
import com.example.person.Person

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMoveActivity.setOnClickListener(this)
        binding.btnMoveActivityData.setOnClickListener(this)
        binding.btnMoveActivityObject.setOnClickListener(this)
        binding.btnHero.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btn_move_activity -> {
                val intent = Intent(this@MainActivity, HalamanKedua::class.java)
                intent.putExtra(HalamanKedua.HALAMAN, "Kedua")
                startActivity(intent)
            }

            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainActivity,MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME,"Royhan")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE,18)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
               val person = Person(
                   "Royhan",
                   5,
                   "royhan@dicoding.com",
                   "Medan"
               )
                val moveWithObjectActivity = Intent(this@MainActivity,MoveWithObjectActivity::class.java)
                moveWithObjectActivity.putExtra(MoveWithObjectActivity.EXTRA_PERSON,person)
                startActivity(moveWithObjectActivity)
            }

            R.id.btnHero -> {
                val hero = Hero(
                    "Sisingamaraja",
                    "Mandailing Natal",
                    "Medan City"
                )

                val intent = Intent(this@MainActivity,com.example.firstapplication.Hero::class.java)
                intent.putExtra(com.example.firstapplication.Hero.USERHERO,hero)
                startActivity(intent)
            }
        }
    }
}