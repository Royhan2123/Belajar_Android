package com.example.storyapp.auth.login

import androidx.lifecycle.*
import com.example.storyapp.api.Result
import com.example.storyapp.api.StoryRepository
import com.example.storyapp.api.response.LoginResponse
import com.example.storyapp.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: StoryRepository, private val userPreference: UserPreference) : ViewModel() {
    private var _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    fun readToken(): LiveData<String> {
        return userPreference.getToken().asLiveData()
    }

    fun readLoginState(): LiveData<Boolean>{
        return userPreference.readState().asLiveData()
    }
    fun loginAccount(email: String, password: String) {
        viewModelScope.launch {
            Result.Loading
            try {
                val result = repository.loginAccount(email, password)
                _loginResult.value = Result.Success(result)
            } catch (e: Exception) {
                _loginResult.value = Result.Error(e.message ?: "Failed to upload story")
            }
        }
    }


}