package com.example.news.repository.news_api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiServiceClient {
    private const val BASE_URL = "https://newsapi.org/"

    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("x-api-key", "a081835fb7244610b72211a893b667e8")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    fun providesApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .build()
            .create(ApiService::class.java)
    }
}