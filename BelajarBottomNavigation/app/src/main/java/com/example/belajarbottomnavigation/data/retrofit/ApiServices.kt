package com.example.belajarbottomnavigation.data.retrofit

import com.example.belajarbottomnavigation.data.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id : String
    ) : Call<RestaurantResponse>
}