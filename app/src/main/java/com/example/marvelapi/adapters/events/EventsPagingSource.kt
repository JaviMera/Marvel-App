package com.example.marvelapi.adapters.events

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.models.events.Event
import com.example.marvelapi.models.events.EventsResponse
import com.example.marvelapi.network.repositories.NetworkEventsInterface

private const val EVENTS_STARTING_OFFSET = 0

class EventsPagingSource(
    private val eventsRepository: NetworkEventsInterface
) : PagingSource<Int, Event>(){
    override fun getRefreshKey(state: PagingState<Int, Event>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        val page = params.key ?: EVENTS_STARTING_OFFSET
        return try{
            when(val response = eventsRepository.getAll(page, EVENTS_PAGE_SIZE)){
                is MarvelApiResult.Error -> {
                    LoadResult.Error(response.error)
                }
                is MarvelApiResult.Success -> {
                    val result = response.data as EventsResponse
                    LoadResult.Page(
                        data = result.data.results,
                        prevKey = if (page == EVENTS_STARTING_OFFSET) null else page - EVENTS_PAGE_SIZE,
                        nextKey = if (page == result.data.total - 1) null else page + EVENTS_PAGE_SIZE
                    )
                }
            }
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }

    companion object{
        const val EVENTS_PAGE_SIZE = 20
    }
}