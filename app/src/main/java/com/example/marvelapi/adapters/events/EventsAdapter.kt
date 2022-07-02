package com.example.marvelapi.adapters.events

import com.example.marvelapi.R
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.common.RecyclerBindingInterface
import com.example.marvelapi.databinding.EventListItemBinding
import com.example.marvelapi.models.events.Event

class EventsAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerAdapterBase<Event, EventListItemBinding>(
    object: RecyclerBindingInterface<Event, EventListItemBinding> {
        override fun bind(item: Event, binder: EventListItemBinding) {
            binder.event = item
            binder.executePendingBindings()
        }
    },
    onItemClickListener,
    R.layout.event_list_item
)