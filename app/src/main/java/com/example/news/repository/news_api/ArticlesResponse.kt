package com.example.news.repository.news_api

import lt.vcs.demoapp.repository.newsapi.Article

class ArticlesResponse (
    val status: String = "",
    val articles: MutableList<Article> = mutableListOf()
)