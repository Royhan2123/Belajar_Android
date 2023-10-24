package com.example.submissiondicoding.api
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.submissiondicoding.api.response.LoginResponse
import com.example.submissiondicoding.api.response.LoginResult
import com.example.submissiondicoding.api.response.RegisterResponse
import com.example.submissiondicoding.api.response.StoryDetail
import com.example.submissiondicoding.api.response.StoryItem
import com.example.submissiondicoding.api.response.UploadResponse
import com.example.submissiondicoding.api.retrofit.ApiService
import com.example.submissiondicoding.model.LoginModel
import com.example.submissiondicoding.model.RegisterModel
import com.example.submissiondicoding.preferences.UserPreference
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class StoryRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
) {

    private lateinit var token: String

    suspend fun registerAccount(name: String, email: String, password: String): RegisterResponse {
        return try {
            val requestBody = RegisterModel(name, email, password)
            val response = apiService.registerAccount(requestBody)
            if (response.error) {
                RegisterResponse(true, response.message)
            } else {
                RegisterResponse(false, response.message)
            }
        } catch (e: Exception) {
            RegisterResponse(true, e.message ?: "Failed to register account")
        }
    }


    suspend fun loginAccount(email: String, password: String): LoginResponse {
        Result.Loading
        return try {
            val requestBody = LoginModel(email, password)
            val response = apiService.loginAccount(requestBody)

            if (response.error) {
                LoginResponse(LoginResult(token = ""), true, response.message)

            } else {
                token = response.loginResult.token
                userPreference.saveToken(token)
                userPreference.login()

                Result.Success(response.message)
                LoginResponse(response.loginResult, false, response.message)
            }
        } catch (e: Exception) {
            LoginResponse(LoginResult(token = ""), true, e.message ?: "Failed to login")
        }
    }


    fun getStory(token: String): LiveData<Result<List<StoryItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllStories("Bearer $token")
            val stories = response.listStory
            val storyList = stories?.map { story ->
                StoryItem(
                    story?.photoUrl,
                    story?.createdAt,
                    story?.name,
                    story?.description,
                    story?.lon,
                    story?.id,
                    story?.lat,
                )
            }
            emit(Result.Success(storyList ?: emptyList()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailStory(token: String, id: String): LiveData<Result<StoryDetail>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getStoryDetail("Bearer $token", id)
            val storyItem = response.story
            if (storyItem != null) {
                emit(Result.Success(storyItem))
            } else {
                emit(Result.Error("Failed to retrieve story detail"))
            }
        } catch (e: Exception) {
            emit(Result.Error("An error occurred"))
        }
    }

    fun uploadStory(token: String, description: String, photo: File) {
        val requestPhotoFile = photo.asRequestBody("image/jpg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            photo.name,
            requestPhotoFile
        )
        val photoDescription = description.toRequestBody("text/plain".toMediaType())
        val uploadImageRequest = apiService.uploadStory("Bearer $token", imageMultipart, photoDescription)

        uploadImageRequest.enqueue(object : Callback<UploadResponse> {
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        Result.Success(responseBody.message)
                    }
                } else {
                    Result.Error(response.message())
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                Result.Error(t.message ?: "Failed to upload story")
            }

        })
    }

    companion object {
        @Volatile
        private var instance: StoryRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
        ): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(apiService, userPreference)
            }.also { instance = it }
    }
}