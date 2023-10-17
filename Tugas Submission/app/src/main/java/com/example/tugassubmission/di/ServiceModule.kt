package com.example.tugassubmission.di

import com.example.tugassubmission.data.remote.auth.AuthService
import com.example.tugassubmission.data.remote.story.StoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    fun provideStoryService(retrofit: Retrofit): StoryService = retrofit.create(StoryService::class.java)

}