package com.example.marvelapi.models.characters

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersData(
    @field:Json(name="results") val results: List<Character>,
    @field:Json(name="total") val total: Int,
) : Parcelable