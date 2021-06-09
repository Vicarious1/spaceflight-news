package com.example.spaceflightnews

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spaceflightnews.data.ArticleRepository
import com.example.spaceflightnews.service.ArticleService
import com.example.spaceflightnews.ui.main.MainViewModel

class ViewModelFactory(val application: SpaceflightNewsApplication) : ViewModelProvider.AndroidViewModelFactory(application) {

    private val articleRepository by lazy { ArticleRepository(application.retrofit.create(ArticleService::class.java)) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(articleRepository) as T
        }
        return super.create(modelClass)
    }

}