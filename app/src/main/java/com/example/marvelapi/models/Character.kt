package com.example.marvelapi.models

import android.os.Parcelable
import com.example.marvelapi.common.MarvelItemBase
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    override val id: Int,
    @field:Json(name="name") val name: String,
    @field:Json(name="thumbnail") val thumbnail: Thumbnail
) : Parcelable, MarvelItemBase()

