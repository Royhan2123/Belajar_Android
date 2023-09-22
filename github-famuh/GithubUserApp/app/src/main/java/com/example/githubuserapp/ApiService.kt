package com.example.githubuserapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // search
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") username: String? = null,
    ): Call<SearchResponse>

    // get detail user
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String,
    ): Call<DetailUserResponse>

    // Follower
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollower(
        @Path("username") username: String,
    ): Call<List<FollowItem>>

// Following
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("username") username: String,
    ): Call<List<FollowItem>>

}