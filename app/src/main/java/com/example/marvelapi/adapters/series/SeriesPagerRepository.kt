package com.example.marvelapi.adapters.series

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.marvelapi.models.series.Series
import com.example.marvelapi.network.repositories.NetworkComicsInterface
import com.example.marvelapi.network.repositories.NetworkSeriesInterface

class SeriesPagerRepository(
    private val repository: NetworkSeriesInterface
) : SeriesPagerInterface {
    override fun seriesPagingData(): LiveData<PagingData<Series>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 100),
            pagingSourceFactory = { SeriesPagingSource(repository) }
        ).liveData
    }
}