package com.example.tugassubmission.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugassubmission.data.remote.ApiResponse
import com.example.tugassubmission.data.remote.auth.AuthBody
import com.example.tugassubmission.data.remote.auth.AuthResponse
import com.example.tugassubmission.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    fun registerUser(authBody: AuthBody): LiveData<ApiResponse<Response<AuthResponse>>> {
        val result = MutableLiveData<ApiResponse<Response<AuthResponse>>>()
        viewModelScope.launch {
            authRepository.registerUser(authBody).collect {
                result.postValue(it)
            }
        }
        return result
    }

}