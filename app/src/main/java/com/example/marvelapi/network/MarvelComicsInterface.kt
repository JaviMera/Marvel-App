package com.example.marvelapi.network

import com.example.marvelapi.models.comics.ComicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelComicsInterface{
    @GET("v1/public/comics")
    suspend fun getComics(@Query("offset") offset: Int, @Query("limit") limit: Int) : Response<ComicsResponse>
}