package com.example.news.news_details_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import coil.load
import coil.size.ViewSizeResolver
import com.example.news.databinding.FragmentNewsDetailsBinding
import com.example.news.news_list_fragment.NewsListFragment
import lt.vcs.demoapp.repository.newsapi.Article

class NewsDetailsFragment : Fragment() {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiveDataFromNewsListFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun receiveDataFromNewsListFragment() {
        setFragmentResultListener(NewsListFragment.REQUEST_KEY_ARTICLE) { requestKey, bundle ->
            val article = bundle.getParcelable<Article>(NewsListFragment.KEY_SOURCE_ARTICLE)
            binding.apply {
                authorTextView.text = article?.author
                dateTextView.text = article?.publishedAt?.substring(0, 10)
                titleTextView.text = article?.title
                descriptionTextView.text = article?.description
                contentTextView.text = article?.content
                urlTextView.text = article?.url
                val photoPath = article?.urlToImage
                articleIImageView.load(photoPath) {
                    crossfade(enable = true)
                    crossfade(durationMillis = 500)
                    size(ViewSizeResolver(articleIImageView))
                }
            }
        }
    }

    companion object {
        const val TAG = "article_details_fragment"
        fun newInstance() = NewsDetailsFragment()
    }
}