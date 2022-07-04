package com.example.marvelapi.viewmodels.characters

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.adapters.characters.CharactersPagerInterface
import com.example.marvelapi.common.SingleLiveData
import com.example.marvelapi.models.characters.Character
import com.example.marvelapi.models.characters.CharactersResponse
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import kotlinx.coroutines.launch

class CharactersViewModel (
    private val repository: CharactersPagerInterface,
    private val characterRepository: NetworkCharactersInterface
    ) : ViewModel() {

    private val _character = SingleLiveData<Character>()
    val character: LiveData<Character>
    get() = _character

    fun getCharacters() : LiveData<PagingData<Character>> {

        return repository
            .charactersPagingData()
            .map { it.map{character -> character} }
            .cachedIn(viewModelScope)
    }

    fun getCharacter(characterId: Int) {
        viewModelScope.launch {
            when(val result = characterRepository.getSingle(characterId)){
                is MarvelApiResult.Error ->{
                    Log.e("CharacterViewModel", result.error.message.toString())
                }
                is MarvelApiResult.Success -> {
                    val response = result.data as CharactersResponse
                    _character.postValue(response.data.results.firstOrNull())
                }
            }
        }
    }
}