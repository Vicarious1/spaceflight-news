package com.example.spaceflightnews.ui.main

import androidx.lifecycle.*
import com.example.spaceflightnews.data.ArticleModel
import com.example.spaceflightnews.data.ArticleRepository
import com.example.spaceflightnews.service.ArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val articleRepository: ArticleRepository, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    //Note: Ideally I would update a table in Room with checked state and have that trigger a LiveData observer, however I'm running short on time
    val readLaterArticleIds = MutableLiveData<List<String>>(listOf())

    fun startRetrievingArticles() {
        viewModelScope.launch(ioDispatcher) {
            articleRepository.startLoadingArticles()
        }
    }

    fun getArticles(): LiveData<List<ArticleModel>> {
        val outData = MediatorLiveData<List<ArticleModel>>()
        val articlesLiveData = articleRepository.getArticles()

        outData.addSource(articlesLiveData) { outData.value = mapArticleResponsesToArticleModels(it, readLaterArticleIds.value) }
        outData.addSource(readLaterArticleIds) { outData.value = mapArticleResponsesToArticleModels(articlesLiveData.value, it) }

        return outData
    }

    private fun mapArticleResponsesToArticleModels(responses: List<ArticleResponse>?, readLaterArticleIds: List<String>?): List<ArticleModel> {
        return responses
            ?.filter { it.articleId != null }
            ?.map {
            ArticleModel (
                articleId = it.articleId ?: "",
                title = it.title ?: "" ,
                url = it.url ?: "",
                readLater = readLaterArticleIds?.contains(it.articleId) == true
            )
        } ?: listOf()
    }

    fun onReadLaterTapped(articleId: String) {
        val articleIds = readLaterArticleIds.value
        val mutableArticleIds = articleIds?.toMutableList() ?: mutableListOf()
        if (mutableArticleIds.contains(articleId)) {
            mutableArticleIds.remove(articleId)
        } else {
            mutableArticleIds.add(articleId)
        }
        readLaterArticleIds.postValue(mutableArticleIds.toList())
    }
}