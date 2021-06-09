package com.example.spaceflightnews.ui.main

interface ArticleCardListener {
    fun onReadLaterTapped(articleId: String)
    fun onShareTapped(articleUrl: String)
}