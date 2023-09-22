package com.example.githubuserapp.data.remote

import com.example.githubuserapp.data.remote.retrofit.ApiService
import com.example.githubuserapp.utils.AppExecutors
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.data.local.entity.UserEntity
import com.example.githubuserapp.data.local.room.UserDao

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val appExecutors: AppExecutors,
) {
    private val result = MediatorLiveData<Result<List<UserEntity>>>()

    fun getHeadlineUser(): LiveData<Result<List<UserEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers()
            val users = response.items
            val userList = users.map { user ->
                val isFavorited = userDao.isUserFavorited(user.login)
                UserEntity(
                    user.login,
                    user.avatarUrl,
                    isFavorited
                )
            }
            userDao.deleteAll()
            userDao.insertUser(userList)
        } catch (e: Exception) {
            Log.d("UserRepository", "getHeadlineUser: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<UserEntity>>> =
            userDao.getUser().map { Result.Success(it) }
        emitSource(localData)
    }

    fun getSearchUser(query: String): LiveData<Result<List<UserEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getSearchUser(query)
            val users = response.items
            val userList = users.map { user ->
                val isFavorited = userDao.isUserFavorited(user.login)
                UserEntity(
                    user.login,
                    user.avatarUrl,
                    isFavorited
                )
            }
            userDao.deleteAll()
            userDao.insertUser(userList)
        } catch (e: Exception) {
            Log.d("UserRepository", "getSearchUser: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<UserEntity>>> =
            userDao.getUser().map { Result.Success(it) }
        emitSource(localData)
    }

    // mengatur dan mengambil bookmark
    fun getFavoritedUser(): LiveData<List<UserEntity>> {
        return userDao.getFavoritedUser()
    }

    suspend fun setFavoritedUser(user: UserEntity, favoriteState: Boolean) {
        user.isFavorited = favoriteState
        userDao.updateUser(user)

    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao,
            appExecutors: AppExecutors,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userDao, appExecutors)
            }.also { instance = it }
    }
}