package com.example.news.news_details_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.news.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {

    private val viewModel: NewsDetailsFragmentViewModel by viewModels()

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = NewsDetailsFragment()
    }
}