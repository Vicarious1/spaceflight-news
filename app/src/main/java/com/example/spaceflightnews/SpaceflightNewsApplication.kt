package com.example.spaceflightnews

import android.app.Application
import com.example.spaceflightnews.service.ArticleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class SpaceflightNewsApplication : Application() {

    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}