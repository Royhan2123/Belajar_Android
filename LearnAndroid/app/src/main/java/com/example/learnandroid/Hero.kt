package com.example.learnandroid

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Hero(
    val name:String,
    val description:String,
    val photo:Int
) : Parcelable
