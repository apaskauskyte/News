package com.example.news.news_source_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.news.databinding.FragmentNewsSourceBinding

class NewsSourceFragment : Fragment() {

    private val viewModel: NewsSourceFragmentViewModel by viewModels()

    private var _binding: FragmentNewsSourceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = NewsSourceFragment()
    }
}