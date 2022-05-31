package com.example.marvelapi.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.adapters.ComicsPagerInterface

class ComicsViweModel (
    private val repository: ComicsPagerInterface
) : ViewModel() {

    fun getComics() : LiveData<PagingData<Comic>> {

        return repository
            .comicsPagingData()
            .map { it.map{comic -> comic} }
            .cachedIn(viewModelScope)
    }
}