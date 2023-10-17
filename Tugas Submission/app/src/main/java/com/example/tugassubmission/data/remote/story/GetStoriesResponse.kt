package com.example.tugassubmission.data.remote.story
import com.example.tugassubmission.data.model.Story
import com.google.gson.annotations.SerializedName

data class GetStoriesResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: List<Story>
)