package com.example.news.news_details_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.vcs.demoapp.repository.newsapi.Article

class NewsDetailsViewModel : ViewModel() {

    private val _articleLiveData = MutableLiveData<Article>()
    val articleLiveData: LiveData<Article>
        get() = _articleLiveData

    fun saveArticleState(article: Article) {
        _articleLiveData.value = article
    }
}