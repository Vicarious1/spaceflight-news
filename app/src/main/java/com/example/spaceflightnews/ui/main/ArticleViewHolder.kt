package com.example.spaceflightnews.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnews.R
import com.example.spaceflightnews.service.ArticleResponse
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val checkBox: MaterialCheckBox = itemView.findViewById(R.id.read_later_checkbox)
    private val shareButton: MaterialButton = itemView.findViewById(R.id.share_button)

    fun bindData(article: ArticleResponse, articleCardListener: ArticleCardListener) {
        title.text = article.title
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            articleCardListener.onReadLaterChecked(isChecked, article.articleId ?: "")
        }
        shareButton.setOnClickListener {
            articleCardListener.onShareTapped(article.url ?: "")
        }
    }
}