package com.example.marvelapi.viewmodels.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelapi.adapters.events.EventsPagerInterface
import com.example.marvelapi.models.events.Event

class EventsViewModel (
    private val repository: EventsPagerInterface
) : ViewModel() {

    fun getEvents() : LiveData<PagingData<Event>> {

        return repository
            .eventsPagingData()
            .map { it.map{series -> series} }
            .cachedIn(viewModelScope)
    }
}