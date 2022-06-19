package com.example.marvelapi.viewmodels.characters

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.adapters.characters.CharactersPagerInterface
import com.example.marvelapi.models.characters.Character

class CharactersViewModel (
    private val repository: CharactersPagerInterface
    ) : ViewModel() {

    fun getCharacters() : LiveData<PagingData<Character>> {

        return repository
            .charactersPagingData()
            .map { it.map{character -> character} }
            .cachedIn(viewModelScope)
    }
}