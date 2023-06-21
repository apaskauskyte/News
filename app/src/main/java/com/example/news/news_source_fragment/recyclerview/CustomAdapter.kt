package com.example.news.news_source_fragment.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.news.databinding.FragmentNewsSourceListBinding
import com.example.news.repository.news_api.Source

class CustomAdapter(
    private val onClick: (Source) -> Unit
) : ListAdapter<Source, CustomViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(
        FragmentNewsSourceListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onClick
    )

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Comparator : DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Source, newItem: Source) =
            oldItem == newItem
    }
}