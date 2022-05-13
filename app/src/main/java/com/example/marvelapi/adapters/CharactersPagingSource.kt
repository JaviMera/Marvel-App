package com.example.marvelapi.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.models.Character
import com.example.marvelapi.models.CharactersErrorResponse
import com.example.marvelapi.models.CharactersResponse
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import com.example.marvelapi.network.repositories.NetworkCharactersRepository

private const val CHARACTERS_STARTING_OFFSET = 0

class CharactersPagingSource(
    private val charactersRepository: NetworkCharactersInterface
) : PagingSource<Int, Character>(){
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: CHARACTERS_STARTING_OFFSET
        return try{
            when(val response = charactersRepository.getAll(page)){
                is MarvelApiResult.Error -> {
                    LoadResult.Error(Exception(response.message))
                }
                is MarvelApiResult.Failure -> {
                    val result = response.data as CharactersErrorResponse
                    LoadResult.Error(Exception(result.message))
                }
                is MarvelApiResult.Success -> {
                    val result = response.data as CharactersResponse
                    LoadResult.Page(
                        data = result.data.results,
                        prevKey = if(page == CHARACTERS_STARTING_OFFSET) null else page -1,
                        nextKey = if(page == result.data.total - 1) null else page + 1
                    )
                }
            }
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }
}