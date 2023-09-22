package com.example.storyapp.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.api.Result
import com.example.storyapp.api.StoryRepository
import com.example.storyapp.api.response.LoginResponse
import com.example.storyapp.api.response.RegisterResponse
import kotlinx.coroutines.launch


class SignupViewModel(private val repository: StoryRepository) : ViewModel() {
    private var _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> get() = _registerResult


    fun registerAccount(name: String, email: String, password: String) {
        viewModelScope.launch {
            Result.Loading
            try {
                val result = repository.registerAccount(name, email, password)
                _registerResult.value = Result.Success(result)
            } catch (e: Exception) {
                _registerResult.value = Result.Error(e.message ?: "Failed to register account")
            }
        }
    }
}