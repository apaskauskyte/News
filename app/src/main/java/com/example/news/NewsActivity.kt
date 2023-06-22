package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.news.databinding.ActivityNewsBinding
import com.example.news.news_details_fragment.NewsDetailsFragment
import com.example.news.news_list_fragment.NewsListFragment
import com.example.news.news_source_fragment.NewsSourceFragment

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openNewsSourcesFragment()
        }
    }

    private fun openNewsSourcesFragment() {
        setCurrentFragment(NewsSourceFragment.newInstance(), NewsSourceFragment.TAG)
    }

    fun openNewsListFragment() {
        setCurrentFragment(NewsListFragment.newInstance(), NewsListFragment.TAG, true)
    }

    fun openNewsDetailsFragment() {
        setCurrentFragment(NewsDetailsFragment.newInstance(), NewsDetailsFragment.TAG, true)
    }

    private fun setCurrentFragment(fragment: Fragment, tag: String, addBackStack: Boolean = false) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_container_view,
                fragment,
                tag
            )

            setReorderingAllowed(true)

            if (addBackStack){
                addToBackStack(tag)
            }
        }
    }
}