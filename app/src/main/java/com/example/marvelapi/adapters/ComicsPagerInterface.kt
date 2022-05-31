package com.example.marvelapi.adapters

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.comics.Comic

interface ComicsPagerInterface{
    fun comicsPagingData() : LiveData<PagingData<Comic>>
}