package com.example.marvelapi

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.adapters.CharactersPagerInterface
import com.example.marvelapi.adapters.CharactersPagerRepository
import com.example.marvelapi.models.Character
import com.example.marvelapi.models.CharactersErrorResponse
import com.example.marvelapi.models.CharactersResponse
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import com.example.marvelapi.network.repositories.NetworkCharactersRepository
import kotlinx.coroutines.launch

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