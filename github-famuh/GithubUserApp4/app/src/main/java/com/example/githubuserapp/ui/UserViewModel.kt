package com.example.githubuserapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.local.entity.UserEntity
import com.example.githubuserapp.data.remote.UserRepository
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    fun getHeadlineUser() = userRepository.getHeadlineUser()

    fun getSearchUser(query: String) = userRepository.getSearchUser(query)

    fun getFavoritedUser() = userRepository.getFavoritedUser()

    fun saveUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.setFavoritedUser(user, true)
        }
    }

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.setFavoritedUser(user, false)
        }
    }
}