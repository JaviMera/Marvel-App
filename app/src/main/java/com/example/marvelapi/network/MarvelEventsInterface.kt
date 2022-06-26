package com.example.marvelapi.network

import com.example.marvelapi.models.events.EventsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelEventsInterface{
    @GET("v1/public/events")
    suspend fun getEvents(@Query("offset") offset: Int, @Query("limit") limit: Int) : Response<EventsResponse>
}