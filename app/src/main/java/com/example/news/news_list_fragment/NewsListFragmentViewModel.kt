package com.example.news.news_list_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.news_api.ArticlesResponse
import com.example.news.repository.news_api.NewsApiServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsListFragmentViewModel : ViewModel() {

    private val _topNewsStateFlow: MutableStateFlow<ArticlesResponse?> =
        MutableStateFlow(ArticlesResponse())
    val topNewsStateFlow = _topNewsStateFlow.asStateFlow()

    fun fetchTopNews(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = NewsApiServiceClient.providesApiService().getTopNews(id)
            _topNewsStateFlow.value = resp.body()
        }
    }
}