package com.example.news.news_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.NewsActivity
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.news_list_fragment.recycleview.ArticleAdapter
import com.example.news.news_source_fragment.NewsSourceFragment
import kotlinx.coroutines.launch
import lt.vcs.demoapp.repository.newsapi.Article

class NewsListFragment : Fragment() {

    private val viewModel: NewsListFragmentViewModel by viewModels()
    private var recyclerAdapter: ArticleAdapter? = null
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeArticleStateFlow()
        receiveDataFromNewsSourceFragment()
    }

    private fun setUpRecyclerView() {
        binding.articleRecyclerView.apply {
            recyclerAdapter = ArticleAdapter { article -> onArticleCLick(article) }
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun onArticleCLick(article: Article) {
        (activity as NewsActivity).openNewsDetailsFragment()
        transferDataToNewsDetailsFragment(article)
    }

    private fun observeArticleStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.topNewsStateFlow.collect { response ->
                    val list = response?.articles

                    if (list != null) {
                        submitArticleList(list)
                    }
                }
            }
        }
    }

    private fun submitArticleList(list: List<Article>) {
        recyclerAdapter?.submitList(list)
        binding.articleRecyclerView.adapter = recyclerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun receiveDataFromNewsSourceFragment() {
        setFragmentResultListener(NewsSourceFragment.REQUEST_KEY_SOURCE) { requestKey, bundle ->
            val sourceId = bundle.getString(NewsSourceFragment.KEY_SOURCE_ID, "")
            viewModel.fetchTopNews(sourceId)
        }
    }

    private fun transferDataToNewsDetailsFragment(article: Article) {
        val bundle = bundleOf(KEY_SOURCE_ARTICLE to article)
        setFragmentResult(REQUEST_KEY_ARTICLE, bundle)
    }

    companion object {
        const val TAG = "article_fragment"
        const val REQUEST_KEY_ARTICLE = "news_article_fragment_result_key"
        const val KEY_SOURCE_ARTICLE = "key_source_article"
        fun newInstance() = NewsListFragment()
    }
}