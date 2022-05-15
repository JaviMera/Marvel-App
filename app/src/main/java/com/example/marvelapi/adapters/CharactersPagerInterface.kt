package com.example.marvelapi.adapters

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.data.local.CharactersRepo
import com.example.marvelapi.models.Character

interface CharactersPagerInterface{
    fun charactersRemotePagingData(context: Context) : LiveData<PagingData<CharactersRepo>>
}

