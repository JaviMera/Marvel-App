package com.example.marvelapi.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelCharactersInterface{
    @GET("v1/public/characters")
    fun getCharacters(@Query("offset") offset: Int): Call<String>
}