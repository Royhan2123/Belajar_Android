package com.example.storyapp.api

import android.util.Log
import androidx.lifecycle.*
import com.example.storyapp.api.response.*
import com.example.storyapp.api.retrofit.ApiService
import com.example.storyapp.model.LoginModel
import com.example.storyapp.model.RegisterModel
import com.example.storyapp.UserPreference
import kotlinx.coroutines.flow.first
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
                Result.Error(response.message)
                RegisterResponse(true, response.message)
            } else {
                Log.e("repo", "Berhasil register email $email")
                Result.Success(response.message)
                RegisterResponse(false, response.message)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Gagal register akun")
            Log.e("repo", "Terjadi kesalahan saat register: ${e.message}")
            RegisterResponse(true, e.message ?: "Gagal register akun")
        }
    }


    suspend fun loginAccount(email: String, password: String): LoginResponse {
        Result.Loading
        return try {
            val requestBody = LoginModel(email, password)
            val response = apiService.loginAccount(requestBody)

            if (response.error) {
                Log.d("repo", "response error")
                Result.Error(response.message)
                LoginResponse(LoginResult(token = ""), true, response.message)

            } else {
                Log.e("repo", "Berhasil login email $email")
                Log.e("repo", "isLogin ${userPreference.readState().first()}")
                token = response.loginResult.token
                userPreference.saveToken(token)
                userPreference.login()

                Result.Success(response.message)
                LoginResponse(response.loginResult, false, response.message)
            }
        } catch (e: Exception) {
            Log.e("repo", "Terjadi kesalahan saat login: ${e.message}")
            Result.Error(e.message ?: "Gagal login akun")
            LoginResponse(LoginResult(token = ""), true, e.message ?: "Gagal login akun")
        }
    }


    fun getHeadlineStory(token: String): LiveData<Result<List<StoryItem>>> = liveData {
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
            Log.d("StoryRepository", "getHeadlineStory: Panjang list ${stories?.size} ")
            emit(Result.Success(storyList ?: emptyList()))
        } catch (e: Exception) {
            Log.d("StoryRepository", "getHeadlineStory: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailStory(token: String, id: String): LiveData<Result<StoryDetail>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getStoryDetail("Bearer $token", id)
            val storyItem = response.story
            Log.d("StoryRepository", "getDetail ID: ${storyItem?.id}")
            if (storyItem != null) {
                emit(Result.Success(storyItem))
            } else {
                emit(Result.Error("Failed to retrieve story detail"))
            }
        } catch (e: Exception) {
            Log.e("StoryRepository", "Error: ${e.message}")
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
                        Log.d("Repo", "Berhasil upload story")
                        Result.Success(responseBody.message)
                    }
                } else {
                    Log.d("Repo", "Gagal upload story : ${response.message()}")
                    Result.Error(response.message())
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                Result.Error(t.message ?: "Gagal upload story, onFailure")
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