package com.example.submissiondicoding.model
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.File
import com.example.story.api.*
import com.example.submissiondicoding.api.StoryRepository
import kotlinx.coroutines.launch


class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    private var _uploadResult = MutableLiveData<Result<Unit>>()
    val uploadResult: LiveData<Result<Unit>> get() = _uploadResult
    fun getStory(token: String) = storyRepository.getStory(token)

    fun getStoryDetail(token: String, id: String) = storyRepository.getDetailStory(token, id)

    fun uploadStory(token: String, description: String, photo: File) {
        viewModelScope.launch {
            com.example.submissiondicoding.api.Result.Loading
            try {
                val result = storyRepository.uploadStory(token, description, photo)
                _uploadResult.value = Result.Success(result)

                // biar refresh
                storyRepository.getStory(token)

            } catch (e: Exception) {
                _uploadResult.value = Result.Error(e.message ?: "Failed to upload story")
            }
        }
    }


}