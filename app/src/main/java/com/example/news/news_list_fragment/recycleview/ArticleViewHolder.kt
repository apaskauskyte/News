package com.example.news.news_list_fragment.recycleview

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import com.example.news.databinding.FragmentNewsListBinding
import lt.vcs.demoapp.repository.newsapi.Article

class ArticleViewHolder(
    private val binding: FragmentNewsListBinding,
    private val onClick: (Article) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentArticle: Article? = null

    init {
        binding.root.setOnClickListener { currentArticle?.let { result -> onClick(result) } }
    }

    fun bind(article: Article) {
        currentArticle = article
        binding.apply {
            titleTextView.text = article.title
            dateTextView.text = article.publishedAt
            descriptionTextView.text = article.description
            val photoPath = article.urlToImage
            articleIImageView.load(photoPath) {
                crossfade(enable = true)
                crossfade(durationMillis = 500)
                size(ViewSizeResolver(articleIImageView))
            }
        }
    }
}