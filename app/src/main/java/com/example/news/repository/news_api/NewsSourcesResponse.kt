package com.example.news.repository.news_api

data class NewsSourcesResponse(
    val status: String = "",
    val sources: MutableList<Source> = mutableListOf()
)