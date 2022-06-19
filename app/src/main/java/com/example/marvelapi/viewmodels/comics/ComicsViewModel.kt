package com.example.marvelapi.viewmodels.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.adapters.comics.ComicsPagerInterface
import com.example.marvelapi.models.comics.Comic

class ComicsViewModel (
    private val repository: ComicsPagerInterface
) : ViewModel() {

    fun getComics() : LiveData<PagingData<Comic>> {

        return repository
            .comicsPagingData()
            .map { it.map{comic -> comic} }
            .cachedIn(viewModelScope)
    }
}