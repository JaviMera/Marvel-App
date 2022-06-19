package com.example.marvelapi.adapters.comics

import com.example.marvelapi.R
import com.example.marvelapi.models.comics.Comic
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.common.RecyclerBindingInterface
import com.example.marvelapi.databinding.ComicListItemBinding

class ComicsAdapter : RecyclerAdapterBase<Comic, ComicListItemBinding>(
    object: RecyclerBindingInterface<Comic, ComicListItemBinding> {
        override fun bind(item: Comic, binder: ComicListItemBinding) {
            binder.comic = item
            binder.executePendingBindings()
        }
    },
    R.layout.comic_list_item
)