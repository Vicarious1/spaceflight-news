package com.example.spaceflightnews.service

import org.json.JSONArray
import retrofit2.Response
import retrofit2.http.GET


interface ArticleService {

    @GET("articles")
    suspend fun getAllArticles(): Response<List<ArticleResponse>>
}