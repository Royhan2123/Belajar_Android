package com.example.belajarbottomnavigation.data.retrofit

import com.example.belajarbottomnavigation.ui.notifications.NotificationsFragment
import com.loopj.android.http.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
     companion object {
         fun getApiServices (): ApiServices {
             val loggingInterceptor = if (BuildConfig.DEBUG) {
                 HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
             } else {
                 HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
             }
             val client = OkHttpClient.Builder()
                 .addInterceptor(loggingInterceptor)
                 .build()
             val retrofit = Retrofit.Builder()
                 .client(client)
                 .baseUrl("https://restaurant-api.dicoding.dev/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
             return retrofit.create(ApiServices::class.java)
         }
     }
}