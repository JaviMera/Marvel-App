package com.example.marvelapi.characters

import com.example.marvelapi.R
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.common.RecyclerBindingInterface
import com.example.marvelapi.databinding.CharacterListItemBinding
import com.example.marvelapi.models.Character

class CharactersAdapter : RecyclerAdapterBase<Character, CharacterListItemBinding>(
    object: RecyclerBindingInterface<Character, CharacterListItemBinding> {
        override fun bind(item: Character, binder: CharacterListItemBinding) {
            binder.character = item
            binder.executePendingBindings()
        }
    },
    R.layout.character_list_item
)

