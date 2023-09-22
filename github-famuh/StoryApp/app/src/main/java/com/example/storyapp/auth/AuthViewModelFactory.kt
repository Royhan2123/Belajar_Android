package com.example.storyapp.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.StoryViewModel
import com.example.storyapp.UI.main.MainViewModel
import com.example.storyapp.api.StoryRepository
import com.example.storyapp.auth.login.LoginViewModel
import com.example.storyapp.UserPreference

class AuthViewModelFactory(private val repository: StoryRepository, private val pref: UserPreference) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            // pref
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }

            // repo
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(repository) as T
            }
            modelClass.isAssignableFrom(StoryViewModel::class.java) -> {
                StoryViewModel(repository) as T
            }

            // both
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository, pref) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}
