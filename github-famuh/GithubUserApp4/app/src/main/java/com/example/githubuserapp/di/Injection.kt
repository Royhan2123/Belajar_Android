package com.example.githubuserapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.githubuserapp.data.local.room.UserDatabase
import com.example.githubuserapp.data.remote.UserRepository
import com.example.githubuserapp.data.remote.retrofit.ApiConfig
import com.example.githubuserapp.preferences.SettingPreferences
import com.example.githubuserapp.utils.AppExecutors


object Injection {
    fun provideRepository(context: Context) : UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        val appExecutors = AppExecutors()
        return UserRepository.getInstance(apiService, dao, appExecutors)
    }
    fun provideSettingPreferences(dataStore: DataStore<Preferences>): SettingPreferences {
        return SettingPreferences.getInstance(dataStore)
    }
}