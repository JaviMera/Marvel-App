package com.example.marvelapi.adapters

import androidx.paging.*
import androidx.paging.LoadType.*
import androidx.room.withTransaction
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.data.local.CharactersRepo
import com.example.marvelapi.data.local.MarvelDatabase
import com.example.marvelapi.data.local.RemoteKeys
import com.example.marvelapi.models.Character
import com.example.marvelapi.models.CharactersErrorResponse
import com.example.marvelapi.models.CharactersRepoResponse
import com.example.marvelapi.models.CharactersResponse
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import retrofit2.HttpException
import java.io.IOException

private const val CHARACTERS_STARTING_OFFSET = 0

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val charactersRepository: NetworkCharactersInterface,
    private val marvelDatabase: MarvelDatabase
) : RemoteMediator<Int, CharactersRepo>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharactersRepo>
    ): MediatorResult {

        val page = when(loadType){
            REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: CHARACTERS_STARTING_OFFSET
            }
            PREPEND -> {
                val remoteKeys = getRemoteKeysForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            return when(val response = charactersRepository.getAll(page)){
                is MarvelApiResult.Error -> {
                    MediatorResult.Error(Exception(response.message))
                }
                is MarvelApiResult.Failure -> {
                    val result = response.data as CharactersErrorResponse
                    MediatorResult.Error(Exception(result.message))
                }
                is MarvelApiResult.Success -> {
                    val result = response.data as CharactersRepoResponse
                    val characters = result.data.results
                    val endOfPagination = characters.isEmpty()

                    marvelDatabase.withTransaction{
                        // clear all tables in the database
                        if(loadType == REFRESH){
                            marvelDatabase.remoteKeysDao().clearRemoteKeys()
                            marvelDatabase.charactersDao().clearCharacters()
                        }

                        val prevKey = if(page == CHARACTERS_STARTING_OFFSET) null else page - 1
                        val nextKey = if(endOfPagination) null else page + 1
                        val keys = characters.map {
                            RemoteKeys(characterId = it.id, prevKey = prevKey, nextKey = nextKey)
                        }

                        marvelDatabase.remoteKeysDao().insertAll(keys)
                        marvelDatabase.charactersDao().insertAll(characters)
                    }

                    MediatorResult.Success(endOfPaginationReached = endOfPagination)
                }
            }
        }catch (exception: IOException){
            return MediatorResult.Error(exception)
        }catch(exception: HttpException){
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharactersRepo>): RemoteKeys?{
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                marvelDatabase.remoteKeysDao().remoteKeysCharacterId(character.id)
            }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, CharactersRepo>): RemoteKeys?{
        return state.pages.firstOrNull(){ it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { character ->
                marvelDatabase.remoteKeysDao().remoteKeysCharacterId(character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharactersRepo>
    ) : RemoteKeys?{
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { characterId ->
                marvelDatabase.remoteKeysDao().remoteKeysCharacterId((characterId))
            }
        }
    }
}