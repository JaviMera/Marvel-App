package com.example.marvelapi.adapters.series

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.models.comics.ComicsResponse
import com.example.marvelapi.models.series.Series
import com.example.marvelapi.models.series.SeriesResponse
import com.example.marvelapi.network.repositories.NetworkSeriesInterface

private const val SERIES_STARTING_OFFSET = 0

class SeriesPagingSource(
    private val seriesRepository: NetworkSeriesInterface
) : PagingSource<Int, Series>(){
    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        val page = params.key ?: SERIES_STARTING_OFFSET
        return try{
            when(val response = seriesRepository.getAll(page, SERIES_PAGE_SIZE)){
                is MarvelApiResult.Error -> {
                    LoadResult.Error(response.error)
                }
                is MarvelApiResult.Success -> {
                    val result = response.data as SeriesResponse
                    LoadResult.Page(
                        data = result.data.results,
                        prevKey = if (page == SERIES_STARTING_OFFSET) null else page - SERIES_PAGE_SIZE,
                        nextKey = if (page == result.data.total - 1) null else page + SERIES_PAGE_SIZE
                    )
                }
            }
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }

    companion object{
        const val SERIES_PAGE_SIZE = 100
    }
}