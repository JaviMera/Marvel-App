package com.example.marvelapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.R
import com.example.marvelapi.databinding.CharacterListItemBinding
import com.example.marvelapi.models.Character

class CharactersAdapter : ListAdapter<Character, CharactersAdapter.CharacterViewHolder>(DiffCallback){

    class CharacterViewHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character){
            binding.character = character
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(inflater,  R.layout.character_list_item, parent,false) as CharacterListItemBinding
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        var character = getItem(position)
        holder.bind(character)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}