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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.databinding.FragmentNewsSourceBinding
import com.example.news.news_source_fragment.recyclerview.CustomAdapter
import com.example.news.repository.news_api.Source
import kotlinx.coroutines.launch

class NewsSourceFragment : Fragment() {

    private val viewModel: NewsSourceFragmentViewModel by viewModels()
    private var recyclerAdapter: CustomAdapter? = null

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

        setUpRecyclerView()

        observeNewsSourcesStateFlow()
    }

    private fun setUpRecyclerView() {
        binding.sourceRecyclerView.apply {
            recyclerAdapter = CustomAdapter { source -> }
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
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

    private fun submitNewsSources(list: List<Source>) {
        recyclerAdapter?.submitList(list)
        binding.sourceRecyclerView.adapter = recyclerAdapter
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