package com.example.githubuserapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @field:ColumnInfo(name = "userLogin")
    @field:PrimaryKey(autoGenerate = false)
    var userLogin: String = "",

    @field:ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,

    @field:ColumnInfo(name = "favorited")
    var isFavorite: Boolean
)