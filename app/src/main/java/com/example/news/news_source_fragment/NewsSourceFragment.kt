package com.example.news.news_source_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.R

class NewsSourceFragment : Fragment() {

    private val viewModel: NewsSourceFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_source, container, false)
    }

    companion object {
        fun newInstance() = NewsSourceFragment()
    }
}