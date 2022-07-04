package com.example.marvelapi.network

import com.example.marvelapi.models.characters.Character
import com.example.marvelapi.models.characters.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelCharactersInterface{
    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int): Response<CharactersResponse>

    @GET("v1/public/characters/{character_id}")
    suspend fun getCharacter(
        @Path("character_id") characterId: Int)
    : Response<CharactersResponse>
}

