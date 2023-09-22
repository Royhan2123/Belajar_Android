package com.example.githubuserapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuserapp.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user where favorited = 1")
    fun getFavoriteUser(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: List<UserEntity>)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("DELETE FROM user WHERE favorited = 0")
     suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM user WHERE userLogin = :userLogin AND favorited = 1)")
    suspend fun isUserFavorite(userLogin: String): Boolean
}