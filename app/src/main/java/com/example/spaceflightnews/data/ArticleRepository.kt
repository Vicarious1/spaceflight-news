package com.example.spaceflightnews.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spaceflightnews.service.ArticleResponse
import com.example.spaceflightnews.service.ArticleService

class ArticleRepository(private val articleService: ArticleService) {

    private val articlesLiveData = MutableLiveData<List<ArticleResponse>>()

    suspend fun startLoadingArticles() {
        //todo make call every X seconds to catch any new articles

        val response = articleService.getAllArticles()
        if (response.isSuccessful) {
            val items = response.body()
            items?.let {
                if (it.isNotEmpty()) {
                    articlesLiveData.postValue(it)
                }
            }
        }
    }

    fun getArticles(): LiveData<List<ArticleResponse>> {
        return articlesLiveData
    }
}