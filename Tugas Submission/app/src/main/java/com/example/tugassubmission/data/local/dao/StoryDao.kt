package com.example.tugassubmission.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tugassubmission.data.local.entity.StoryEntity

@Dao
interface StoryDao {

    @Query("SELECT * FROM tbl_story")
    fun getAllStories(): List<StoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertStories(storyList: List<StoryEntity>)

    @Query("DELETE FROM tbl_story")
     fun deleteAllStories()
}