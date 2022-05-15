package com.example.marvelapi.adapters

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.marvelapi.data.local.CharactersRepo
import com.example.marvelapi.data.local.MarvelDatabase
import com.example.marvelapi.models.Character
import com.example.marvelapi.network.repositories.NetworkCharactersInterface

@OptIn(ExperimentalPagingApi::class)
class CharactersPagerRepository(
    private val repository: NetworkCharactersInterface
) : CharactersPagerInterface {

    override fun charactersRemotePagingData(context: Context): LiveData<PagingData<CharactersRepo>> {
        val marvelDatabase = MarvelDatabase.getInstance(context)
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {marvelDatabase.charactersDao().charactersByName()},
            remoteMediator = CharactersRemoteMediator(repository, marvelDatabase)
        ).liveData
    }

    companion object{
        private const val NETWORK_PAGE_SIZE = 20
    }
}