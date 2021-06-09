package com.example.spaceflightnews.ui.main

import androidx.lifecycle.*
import com.example.spaceflightnews.data.ArticleEntity
import com.example.spaceflightnews.data.ArticleRepository
import com.example.spaceflightnews.service.ArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val articleRepository: ArticleRepository, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    fun startRetrievingArticles() {
        viewModelScope.launch(ioDispatcher) {
            articleRepository.startLoadingArticles()
        }
    }

    fun getArticles(): LiveData<List<ArticleResponse>> {
        return articleRepository.getArticles()
    }
}