package com.example.marvelapi.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.comics.Comic
import com.example.marvelapi.comics.ComicsResponse
import com.example.marvelapi.network.repositories.NetworkComicsInterface

private const val COMICS_STARTING_OFFSET = 0

class ComicsPagingSource(
    private val comicsRepository: NetworkComicsInterface
) : PagingSource<Int, Comic>(){
    override fun getRefreshKey(state: PagingState<Int, Comic>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comic> {
        val page = params.key ?: COMICS_STARTING_OFFSET
        return try{
            when(val response = comicsRepository.getAll(page)){
                is MarvelApiResult.Error -> {
                    LoadResult.Error(response.error)
                }
                is MarvelApiResult.Success -> {
                    val result = response.data as ComicsResponse
                    LoadResult.Page(
                        data = result.data.results,
                        prevKey = if (page == COMICS_STARTING_OFFSET) null else page - COMICS_PAGE_SIZE,
                        nextKey = if (page == result.data.total - 1) null else page + COMICS_PAGE_SIZE
                    )
                }
            }
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }

    companion object{
        const val COMICS_PAGE_SIZE = 50
    }
}