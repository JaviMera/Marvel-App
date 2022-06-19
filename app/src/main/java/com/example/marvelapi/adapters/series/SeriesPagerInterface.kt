package com.example.marvelapi.adapters.series

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.models.series.Series

interface SeriesPagerInterface{
    fun seriesPagingData(): LiveData<PagingData<Series>>
}