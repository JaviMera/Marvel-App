package com.example.marvelapi.models.events

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventsData(
    @field:Json(name="results") val results: List<Event>,
    @field:Json(name="total") val total: Int,
) : Parcelable