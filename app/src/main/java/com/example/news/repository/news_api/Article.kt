package lt.vcs.demoapp.repository.newsapi

import android.os.Parcelable
import com.example.news.repository.news_api.Source

import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Article(
    val source: @RawValue Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
) : Parcelable