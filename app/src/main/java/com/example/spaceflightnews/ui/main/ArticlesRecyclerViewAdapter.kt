package com.example.spaceflightnews.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnews.R
import com.example.spaceflightnews.data.ArticleModel
import com.example.spaceflightnews.service.ArticleResponse

class ArticlesRecyclerViewAdapter(private val cardListener: ArticleCardListener) : RecyclerView.Adapter<ArticleViewHolder>() {

    var articles: List<ArticleModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.article_view, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(articles[position], cardListener)
    }
}