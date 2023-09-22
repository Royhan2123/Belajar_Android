package com.example.restaurantreview

open class Event<out T>(private val content: T) { // T adalah tipe generic yang bisa digunakan supaya kelas ini dapat membungkus berbagai macam data.

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
    private set // Allow external read but not write

    fun getContentIfNotHandled(): T? {
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}