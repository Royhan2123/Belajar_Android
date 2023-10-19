package com.example.tugassubmission.data.local
import com.example.tugassubmission.data.local.dao.StoryDao
import com.example.tugassubmission.data.local.entity.StoryEntity
import androidx.room.Database
import androidx.room.RoomDatabase

    @Database(
        entities =[StoryEntity::class],
        version = 1,
        exportSchema = false
    )
    abstract class StoryAppDatabase: RoomDatabase() {

        abstract fun getStoryDao(): StoryDao

    }