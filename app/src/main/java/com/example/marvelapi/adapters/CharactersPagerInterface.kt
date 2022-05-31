package com.example.marvelapi.adapters

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.models.Character

interface CharactersPagerInterface{
    fun charactersPagingData() : LiveData<PagingData<Character>>
}

