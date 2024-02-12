package com.example.belajarbottomnavigation.data.retrofit

import com.example.belajarbottomnavigation.data.retrofit.ApiServices
import com.loopj.android.http.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiServices(): ApiServices {

            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://restaurant-api.dicoding.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }
}