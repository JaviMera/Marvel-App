package com.example.marvelapi.network

import com.example.marvelapi.models.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelCharactersInterface{
    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int): Response<CharactersResponse>
}

