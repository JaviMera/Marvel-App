package com.example.marvelapi.comics

import android.os.Parcelable
import com.example.marvelapi.models.Thumbnail
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    @Json(name="id") val id: Int,
    @Json(name="title") val title: String?,
    @Json(name="thumbnail") val thumbnail: Thumbnail
) : Parcelable

