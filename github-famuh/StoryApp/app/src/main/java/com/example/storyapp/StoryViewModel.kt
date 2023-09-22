package com.example.storyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.api.StoryRepository
import java.io.File
import com.example.storyapp.api.*
import kotlinx.coroutines.launch


class StoryViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    private var _uploadResult = MutableLiveData<Result<Unit>>()
    val uploadResult: LiveData<Result<Unit>> get() = _uploadResult
    fun getHeadlineStory(token: String) = storyRepository.getHeadlineStory(token)

    fun getStoryDetail(token: String, id: String) = storyRepository.getDetailStory(token, id)

    fun uploadStory(token: String, description: String, photo: File) {
        viewModelScope.launch {
            Result.Loading
            try {
                val result = storyRepository.uploadStory(token, description, photo)
                _uploadResult.value = Result.Success(result)

                // refresh list
                storyRepository.getHeadlineStory(token)

            } catch (e: Exception) {
                _uploadResult.value = Result.Error(e.message ?: "Failed to upload story")
            }
        }
    }


}