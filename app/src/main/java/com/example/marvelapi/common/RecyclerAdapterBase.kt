package com.example.marvelapi.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

open class RecyclerAdapterBase<TItem : MarvelItemBase, TViewBinding : ViewDataBinding>(
    private val bindingInterface: RecyclerBindingInterface<TItem, TViewBinding>,
    private val onItemClickListener: OnItemClickListener,
    @LayoutRes val layoutId: Int
) : PagingDataAdapter<TItem, RecyclerAdapterBase.MarvelViewHolder>(DiffCallbackBase()){

    class MarvelViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun <TItem : MarvelItemBase, TViewBinding : ViewDataBinding> bind(
            item: TItem,
            bindingInterface: RecyclerBindingInterface<TItem, TViewBinding>
        ) = bindingInterface.bind(item, binding as TViewBinding)
    }

    class OnItemClickListener(val clickListener: (movieId: Int) -> Unit){
        fun onClick(movieId: Int) = clickListener(movieId)
    }
    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        val item = getItem(position) as TItem
        holder.itemView.setOnClickListener {
            item.id.let {
                onItemClickListener.onClick(it)
            }
        }
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

