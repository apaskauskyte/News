package com.example.news.news_source_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news.databinding.FragmentNewsSourceBinding
import com.example.news.repository.reqres.Source
import kotlinx.coroutines.launch

class NewsSourceFragment : Fragment() {

    private val viewModel: NewsSourceFragmentViewModel by viewModels()

    private var _binding: FragmentNewsSourceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchNewsSources()

        observeNewsSourcesStateFlow()
    }

    private fun bindNewsSources(source: Source) {
        binding.apply {
            nameTextView.text = source.name
            categoryTextView.text = source.category
            descriptionTextView.text = source.description
        }
    }

    private fun submitNewsSources(sources: MutableList<Source>) {
        for (i in 0 until sources.size) {
            bindNewsSources(sources[i])
        }
    }

    private fun observeNewsSourcesStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.newsSourceStateFlow.collect { response ->
                    val list = response?.sources

                    if (list != null) {
                        submitNewsSources(list)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "news_sources_fragment"
        fun newInstance() = NewsSourceFragment()
    }
}