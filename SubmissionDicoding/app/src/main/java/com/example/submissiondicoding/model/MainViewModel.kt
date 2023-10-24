package com.example.submissiondicoding.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.File
import com.example.submissiondicoding.api.StoryRepository
import kotlinx.coroutines.launch
import com.example.submissiondicoding.api.Result

class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    private val _uploadResult = MutableLiveData<Result<Unit>>()
    val uploadResult: LiveData<Result<Unit>> get() = _uploadResult

    fun getStory(token: String) = storyRepository.getStory(token)

    fun getStoryDetail(token: String, id: String) = storyRepository.getDetailStory(token, id)

    fun uploadStory(token: String, description: String, photo: File) {
        viewModelScope.launch {
            _uploadResult.value = Result.Loading
            try {
                // Memanggil fungsi uploadStory dari StoryRepository
                val result = storyRepository.uploadStory(token, description, photo)
                _uploadResult.value = Result.Success(result)

                // Memanggil fungsi getStory dari StoryRepository untuk memperbarui daftar cerita
                storyRepository.getStory(token)

            } catch (e: Exception) {
                _uploadResult.value = Result.Error(e.message ?: "Failed to upload story")
            }
        }
    }
}
