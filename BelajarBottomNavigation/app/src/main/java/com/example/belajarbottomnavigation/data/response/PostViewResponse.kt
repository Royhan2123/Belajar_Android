package com.example.belajarbottomnavigation.data.response

import com.google.gson.annotations.SerializedName

data class PostViewResponse(
    @field: SerializedName("customerReviews")
    val customerReviewsItem: List<CustomerReviewsItem>,

    @field: SerializedName("error")
    val error: Boolean,

    @field: SerializedName("message")
    val message: String,

)
