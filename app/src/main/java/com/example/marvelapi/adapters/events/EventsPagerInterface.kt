package com.example.marvelapi.adapters.events

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.marvelapi.models.events.Event

interface EventsPagerInterface{
    fun eventsPagingData(): LiveData<PagingData<Event>>
}