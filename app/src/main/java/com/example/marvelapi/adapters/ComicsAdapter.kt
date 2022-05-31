package com.example.marvelapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.R
import com.example.marvelapi.comics.Comic
import com.example.marvelapi.databinding.ComicListItemBinding

class ComicsAdapter : PagingDataAdapter<Comic, ComicsAdapter.ComicViewHolder>(DiffCallback){

    class ComicViewHolder(private val binding: ComicListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic){
            binding.comic = comic
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.comic_list_item,
            parent,
            false
        ) as ComicListItemBinding
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        var comic = getItem(position)
        holder.bind(comic!!)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Comic>(){
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }
    }
}