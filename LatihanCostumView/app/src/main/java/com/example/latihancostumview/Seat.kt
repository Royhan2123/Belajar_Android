package com.example.latihancostumview

import android.view.View.getDefaultSize

data class Seat(
    val id: Int,
    var x: Float? = 0F,
    var y: Float? = 0F,
    var name: String,
    var isBooked: Boolean,
)
