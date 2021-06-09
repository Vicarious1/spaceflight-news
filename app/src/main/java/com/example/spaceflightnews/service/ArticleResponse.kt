package com.example.spaceflightnews.service

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("id")
    var articleId: String?,
    @SerializedName("featured")
    var featured: Boolean?,
    @SerializedName("default")
    var default: Boolean?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("imageUrl")
    var imageUrl: String?,
    @SerializedName("newsSite")
    var newsSite: String?,
    @SerializedName("summary")
    var summary: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?
)
