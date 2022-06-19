package com.example.marvelapi.adapters.characters

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.models.characters.Character

interface CharactersPagerInterface{
    fun charactersPagingData() : LiveData<PagingData<Character>>
}

