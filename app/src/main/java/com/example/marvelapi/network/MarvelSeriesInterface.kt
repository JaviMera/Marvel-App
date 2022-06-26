package com.example.marvelapi.network

import com.example.marvelapi.models.series.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelSeriesInterface{
    @GET("v1/public/series")
    suspend fun getSeries(@Query("offset") offset: Int, @Query("limit") limit: Int) : Response<SeriesResponse>
}

