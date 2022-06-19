package com.example.marvelapi.viewmodels.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.adapters.series.SeriesPagerInterface
import com.example.marvelapi.models.comics.Comic
import com.example.marvelapi.models.series.Series

class SeriesViewModel (
    private val repository: SeriesPagerInterface
) : ViewModel() {

    fun getSeries() : LiveData<PagingData<Series>> {

        return repository
            .seriesPagingData()
            .map { it.map{series -> series} }
            .cachedIn(viewModelScope)
    }
}