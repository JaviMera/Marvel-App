package com.example.marvelapi.common

import android.view.View
import androidx.databinding.ViewDataBinding

interface RecyclerBindingInterface<TItem : MarvelItemBase, TViewBinding : ViewDataBinding>{
    fun bind(item: TItem, binder: TViewBinding)
}