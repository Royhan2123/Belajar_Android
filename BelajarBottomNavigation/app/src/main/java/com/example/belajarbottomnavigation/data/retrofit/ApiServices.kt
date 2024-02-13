package com.example.belajarbottomnavigation.data.retrofit

import com.example.belajarbottomnavigation.data.response.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface ApiServices {
    @GET("detai/{id}")
    fun getRestaurant(
        @Path("id") id:String
    ) : Call<RestaurantResponse>
}