package com.example.news.repository.news_api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("language") language: String = "en",
        @Query("country") country: String = "us",
    ): Response<NewsSourcesResponse>
}