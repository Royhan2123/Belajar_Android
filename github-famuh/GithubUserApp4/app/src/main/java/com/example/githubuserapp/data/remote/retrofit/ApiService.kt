package com.example.githubuserapp.data.remote.retrofit


import SearchResponse
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.ui.detail.DetailUserResponse
import com.example.githubuserapp.ui.detail.FollowItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun getUsers(): SearchResponse


    // search
    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
     suspend fun getSearchUser(
        @Query("q") username: String,
    ): SearchResponse

    // get detail user
    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getDetailUser(
        @Path("username") username: String,
    ): Call<DetailUserResponse>

    // Follower
    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserFollower(
        @Path("username") username: String,
    ): Call<List<FollowItem>>

// Following
    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserFollowing(
        @Path("username") username: String,
    ): Call<List<FollowItem>>

}