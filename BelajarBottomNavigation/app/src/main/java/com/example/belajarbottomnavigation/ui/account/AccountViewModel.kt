package com.example.belajarbottomnavigation.ui.account

import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    private var result = 0

    fun calculate(width:String,height:String,length:String) {
        result = width.toInt() * height.toInt() * length.toInt()
    }
}