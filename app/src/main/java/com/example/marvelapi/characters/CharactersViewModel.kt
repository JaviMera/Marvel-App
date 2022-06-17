package com.example.marvelapi.characters

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.characters.CharactersPagerInterface
import com.example.marvelapi.models.Character

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