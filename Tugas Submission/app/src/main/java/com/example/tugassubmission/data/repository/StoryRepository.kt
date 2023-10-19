package com.example.tugassubmission.data.repository
import com.example.tugassubmission.data.remote.story.AddStoriesResponse
import com.example.tugassubmission.data.remote.ApiResponse
import com.example.tugassubmission.data.remote.story.GetStoriesResponse
import com.example.tugassubmission.data.source.StoryDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryRepository @Inject constructor(private val storyDataSource: StoryDataSource) {

    suspend fun getAllStories(token: String): Flow<ApiResponse<GetStoriesResponse>> {
        return storyDataSource.getAllStories(token).flowOn(Dispatchers.IO)
    }

    suspend fun addNewStory(token: String, file: MultipartBody.Part, description: RequestBody): Flow<ApiResponse<AddStoriesResponse>> {
        return storyDataSource.addNewStory(token, file, description)
    }

}