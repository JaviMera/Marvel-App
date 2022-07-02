package com.example.marvelapi.adapters.series

import com.example.marvelapi.R
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.common.RecyclerBindingInterface
import com.example.marvelapi.databinding.SeriesListItemBinding
import com.example.marvelapi.models.series.Series

class SeriesAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerAdapterBase<Series, SeriesListItemBinding>(
    object: RecyclerBindingInterface<Series, SeriesListItemBinding> {
        override fun bind(item: Series, binder: SeriesListItemBinding) {
            binder.series = item
            binder.executePendingBindings()
        }
    },
    onItemClickListener,
    R.layout.series_list_item
)

