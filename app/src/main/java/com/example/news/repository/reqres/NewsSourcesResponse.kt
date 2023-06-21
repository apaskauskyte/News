package com.example.news.repository.reqres

data class NewsSourcesResponse(
    val status: String = "",
    val sources: MutableList<Source> = mutableListOf()
)