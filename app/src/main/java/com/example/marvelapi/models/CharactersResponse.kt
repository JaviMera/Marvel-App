package com.example.marvelapi.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersResponse(
    @field:Json(name="data") val data: CharactersData
) : Parcelable
