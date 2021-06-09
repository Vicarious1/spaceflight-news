package com.example.spaceflightnews.ui.main

interface ArticleCardListener {
    fun onReadLaterChecked(checked: Boolean, articleId: String)
    fun onShareTapped(articleUrl: String)
}