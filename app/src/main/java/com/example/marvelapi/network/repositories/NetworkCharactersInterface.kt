package com.example.marvelapi.network.repositories

import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.models.CharactersErrorResponse
import com.example.marvelapi.models.CharactersResponse
import com.example.marvelapi.network.MarvelCharactersInterface
import com.google.gson.Gson
import retrofit2.awaitResponse

interface NetworkCharactersInterface {
    suspend fun getAll() : MarvelApiResult<*>
}

class NetworkCharactersRepository(
    private val marvelCharactersInterface: MarvelCharactersInterface
) : NetworkCharactersInterface{

    override suspend fun getAll(): MarvelApiResult<*> {

        return try {
            val response = marvelCharactersInterface.getCharacters().awaitResponse()
            if(response.isSuccessful){
                MarvelApiResult.Success(Gson().fromJson(response.body(), CharactersResponse::class.java))
            }else{
                MarvelApiResult.Failure(Gson().fromJson(response.errorBody()?.string(), CharactersErrorResponse::class.java))
            }
        }catch (exception: Exception){
            MarvelApiResult.Error(exception.localizedMessage)
        }
    }
}