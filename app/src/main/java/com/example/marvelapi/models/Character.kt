package com.example.marvelapi.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @field:Json(name="id") val id: Int,
    @field:Json(name="name") val name: String,
    @field:Json(name="thumbnail") val thumbnail: Thumbnail
) : Parcelable

