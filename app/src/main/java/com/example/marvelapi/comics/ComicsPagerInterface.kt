package com.example.marvelapi.comics

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.models.Comic

interface ComicsPagerInterface{
    fun comicsPagingData() : LiveData<PagingData<Comic>>
}