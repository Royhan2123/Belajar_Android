package com.example.tugassubmission.data.mapper
import com.example.tugassubmission.data.model.Story
import com.example.tugassubmission.data.local.entity.StoryEntity

fun storyToStoryEntity(story: Story): StoryEntity {
    return StoryEntity(
        id = story.id,
        photoUrl = story.photoUrl
    )
}