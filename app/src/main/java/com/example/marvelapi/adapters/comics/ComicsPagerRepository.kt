package com.example.marvelapi.adapters.comics

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.marvelapi.models.comics.Comic
import com.example.marvelapi.network.repositories.NetworkComicsInterface

class ComicsPagerRepository(
    private val repository: NetworkComicsInterface
) : ComicsPagerInterface {
    override fun comicsPagingData()
            : LiveData<PagingData<Comic>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 100),
            pagingSourceFactory = { ComicsPagingSource(repository) }
        ).liveData
    }
}

