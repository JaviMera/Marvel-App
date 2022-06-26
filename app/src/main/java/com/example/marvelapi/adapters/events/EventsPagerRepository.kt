package com.example.marvelapi.adapters.events

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.marvelapi.models.events.Event
import com.example.marvelapi.network.repositories.NetworkEventsInterface

class EventsPagerRepository(
    private val repository: NetworkEventsInterface
) : EventsPagerInterface {
    override fun eventsPagingData()
            : LiveData<PagingData<Event>> {

        Log.i("Events Pager", "Calling pager source")
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 60),
            pagingSourceFactory = { EventsPagingSource(repository) }
        ).liveData
    }
}