package com.example.marvelapi.common

import androidx.recyclerview.widget.DiffUtil

class DiffCallbackBase<TItem : MarvelItemBase> : DiffUtil.ItemCallback<TItem>(){
    override fun areItemsTheSame(oldItem: TItem, newItem: TItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TItem, newItem: TItem): Boolean {
        return oldItem == newItem
    }
}