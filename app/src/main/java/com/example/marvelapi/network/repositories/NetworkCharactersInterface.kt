package com.example.marvelapi.network.repositories

import android.util.Log
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.models.CharactersErrorResponse
import com.example.marvelapi.models.CharactersRepoResponse
import com.example.marvelapi.models.CharactersResponse
import com.example.marvelapi.network.MarvelCharactersInterface
import com.google.gson.Gson
import retrofit2.awaitResponse

interface NetworkCharactersInterface {
    suspend fun getAll(offset: Int): MarvelApiResult<*>
}

class NetworkCharactersRepository(
    private val marvelCharactersInterface: MarvelCharactersInterface
) : NetworkCharactersInterface{

    override suspend fun getAll(offset: Int): MarvelApiResult<*> {

        return try {
            val response = marvelCharactersInterface.getCharacters(offset).awaitResponse()
            if(response.isSuccessful){
                MarvelApiResult.Success(Gson().fromJson(response.body(), CharactersRepoResponse::class.java))
            }else{
                val errorBody = response.errorBody()?.string()
                MarvelApiResult.Failure(Gson().fromJson(errorBody, CharactersErrorResponse::class.java))
            }
        }catch (exception: Exception){
            MarvelApiResult.Error(exception.localizedMessage)
        }
    }
}