package com.example.belajarbottomnavigation.data.response

import com.google.gson.annotations.SerializedName

data class PostReviewReponse(
    @field: SerializedName("customerReviews")
    val customerReviewsItem: List<CustomerReviewsItem>,

    @field: SerializedName("message")
    val message: String,

    @field: SerializedName("error")
    val error: Boolean,
)
