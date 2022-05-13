package com.example.marvelapi.adapters

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
            config = PagingConfig(enablePlaceholders = false, pageSize =  NETWORK_PAGE_SIZE),
            pagingSourceFactory = {CharactersPagingSource(repository)}
        ).liveData
    }

    companion object{
        private const val NETWORK_PAGE_SIZE = 20
    }
}