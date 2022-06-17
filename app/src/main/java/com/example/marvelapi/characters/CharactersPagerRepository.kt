package com.example.marvelapi.characters

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.marvelapi.models.Character
import com.example.marvelapi.network.repositories.NetworkCharactersInterface

class CharactersPagerRepository(
    private val repository: NetworkCharactersInterface
) : CharactersPagerInterface {
    override fun charactersPagingData()
    : LiveData<PagingData<Character>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize =  20),
            pagingSourceFactory = { CharactersPagingSource(repository) }
        ).liveData
    }
}

