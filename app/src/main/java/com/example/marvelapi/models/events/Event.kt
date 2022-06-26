package com.example.marvelapi.models.events

import android.os.Parcelable
import com.example.marvelapi.common.MarvelItemBase
import com.example.marvelapi.models.Thumbnail
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    override val id: Int,
    @Json(name="title") val title: String?,
    @Json(name="thumbnail") val thumbnail: Thumbnail

) : Parcelable, MarvelItemBase()