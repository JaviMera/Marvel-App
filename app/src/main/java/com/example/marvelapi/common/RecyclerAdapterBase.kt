package com.example.marvelapi.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.R
import com.example.marvelapi.databinding.CharacterListItemBinding
import com.example.marvelapi.models.Character

open class RecyclerAdapterBase<TItem : MarvelItemBase, TViewBinding : ViewDataBinding>(
    private val bindingInterface: RecyclerBindingInterface<TItem, TViewBinding>,
    @LayoutRes val layoutId: Int
) : PagingDataAdapter<TItem, RecyclerAdapterBase.MarvelViewHolder>(DiffCallbackBase()){

    class MarvelViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun <TItem : MarvelItemBase, TViewBinding : ViewDataBinding> bind(
            item: TItem,
            bindingInterface: RecyclerBindingInterface<TItem, TViewBinding>
        ) = bindingInterface.bind(item, binding as TViewBinding)
    }
    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        val item = getItem(position) as TItem
        holder.bind(item, bindingInterface)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            layoutId,
            parent,
            false
        )
        return MarvelViewHolder(binding)
    }
}

