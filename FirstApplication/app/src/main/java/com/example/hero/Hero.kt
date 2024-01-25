package com.example.hero

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Hero(
    val name:String?,
    val addres:String?,
    val city:String?,
) : Parcelable

