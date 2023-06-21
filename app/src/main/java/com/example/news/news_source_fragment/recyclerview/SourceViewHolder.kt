package com.example.news.news_source_fragment.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.FragmentNewsSourceListBinding
import com.example.news.repository.news_api.Source

class SourceViewHolder(
    private val binding: FragmentNewsSourceListBinding,
    private val onClick: (Source) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentSource: Source? = null

    init {
        binding.root.setOnClickListener { currentSource?.let { source -> onClick(source) } }
    }

    fun bind(source: Source) {
        currentSource = source
        binding.apply {
            nameTextView.text = source.name
            categoryTextView.text = source.category
            descriptionTextView.text = source.description
        }
    }
}