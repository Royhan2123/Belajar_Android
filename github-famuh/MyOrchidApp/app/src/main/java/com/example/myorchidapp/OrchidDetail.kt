package com.example.myorchidapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myorchidapp.databinding.ItemDetailBinding

class OrchidDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ItemDetailBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.action_share -> {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, "Pengiriman dari Detail Activity ^^")
                val chooser = Intent.createChooser(share, "Bagikan dengan . . .")
                startActivity(chooser)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionShare.setOnClickListener(this)

        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, Orchid::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (data != null) {
            val dataName = data.name
            val dataSpec = data.species
            val dataDesc = data.description
            val dataImg = data.photo

            Glide.with(applicationContext)
                .load(dataImg)
                .into(binding.imgItemDetail)
            binding.itemNameDetail.text = dataName
            binding.itemSpeciesDetail.text = dataSpec
            binding.itemDescDetail.text = dataDesc
        }
    }


}