package com.example.belajarbottomnavigation.data.retrofit

import com.example.belajarbottomnavigation.PostReviewResponse
import com.example.belajarbottomnavigation.data.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id : String
    ) : Call<RestaurantResponse>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id") id :String,
        @Field("name") name :String,
        @Field("review") review :String,
    ) : Call<PostReviewResponse>
}