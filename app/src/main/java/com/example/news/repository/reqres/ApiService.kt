package com.example.news.repository.reqres

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String = "a081835fb7244610b72211a893b667e8",
        @Query("language") language: String = "en",
        @Query("country") country: String = "us",
    ): Response<NewsSourcesResponse>
}