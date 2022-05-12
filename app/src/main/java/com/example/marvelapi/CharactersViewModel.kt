package com.example.marvelapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapi.models.Character
import com.example.marvelapi.models.CharactersErrorResponse
import com.example.marvelapi.models.CharactersResponse
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import kotlinx.coroutines.launch

class CharactersViewModel (
    private val charactersRepository: NetworkCharactersInterface
    ) : ViewModel() {

        private val _characters = MutableLiveData<List<Character>>()
        val characters: LiveData<List<Character>>
        get() = _characters

        fun getCharacters(){
            viewModelScope.launch {

                try {
                    when(val response = charactersRepository.getAll(DEFAULT_OFFSET_VALUE)){
                        is MarvelApiResult.Success -> {
                            val characters = response.data as CharactersResponse
                            characters.data.results.let {
                                _characters.postValue(it)
                            }
                        }
                        is MarvelApiResult.Failure -> {
                            val failureResponse = response.data as CharactersErrorResponse
                            Log.i("CharactersViewModeL", failureResponse.message)
                        }
                        is MarvelApiResult.Error -> {
                            Log.i("CharactersViewModeL", response.message!!)
                        }
                    }
                }catch(exception: Exception){
                    Log.i("CharactersViewModeL", exception.localizedMessage!!)
                }
            }
        }

    companion object{
        const val DEFAULT_OFFSET_VALUE = 0
    }
}