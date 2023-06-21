package lt.vcs.demoapp.repository.newsapi

import com.example.news.repository.news_api.Source

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)