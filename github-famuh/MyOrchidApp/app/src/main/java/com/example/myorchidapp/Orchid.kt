package com.example.myorchidapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Orchid(
    val name: String,
    val description: String,
    val species: String,
    val photo: String
) : Parcelable
