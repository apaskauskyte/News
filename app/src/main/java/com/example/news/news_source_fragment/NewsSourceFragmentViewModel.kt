package com.example.news.news_source_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.reqres.NewsSourcesResponse
import com.example.news.repository.reqres.NewsApiServiceClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsSourceFragmentViewModel : ViewModel() {

    private val _newsSourceStateFlow: MutableStateFlow<NewsSourcesResponse?> =
        MutableStateFlow(NewsSourcesResponse())

    val newsSourceStateFlow = _newsSourceStateFlow.asStateFlow()

    fun fetchNewsSources() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = NewsApiServiceClient.providesApiService().getNewsSources()
            _newsSourceStateFlow.value = response.body()
        }
    }
}