package com.example.LatihanIntent.hero

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Person(
    val name:String?,
    val addres:String?,
    val city:String?,
) : Parcelable

