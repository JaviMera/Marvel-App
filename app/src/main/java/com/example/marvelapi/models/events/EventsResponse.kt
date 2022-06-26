package com.example.marvelapi.models.events

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventsResponse(
    @field:Json(name="data") val data: EventsData
) : Parcelable