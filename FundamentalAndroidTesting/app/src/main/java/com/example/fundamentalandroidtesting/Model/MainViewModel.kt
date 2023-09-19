package com.example.fundamentalandroidtesting.Model

import com.example.fundamentalandroidtesting.CuboidModel

class MainViewModel(private val cuboidModel: CuboidModel) {
    fun getCircumference() = cuboidModel.getCircumference()
    fun getSurfaceArea() = cuboidModel.getSurfaceArea()
    fun getVolume() = cuboidModel.getVolume()
    fun save(w: Double, l: Double, h: Double) {
        cuboidModel.save(w, l, h)

    }
}