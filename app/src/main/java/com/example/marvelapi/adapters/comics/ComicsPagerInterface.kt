package com.example.marvelapi.adapters.comics

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.models.comics.Comic

interface ComicsPagerInterface{
    fun comicsPagingData() : LiveData<PagingData<Comic>>
}