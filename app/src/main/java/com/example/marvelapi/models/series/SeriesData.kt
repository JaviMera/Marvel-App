package com.example.marvelapi.models.series

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesData(
    @field:Json(name="results") val results: List<Series>,
    @field:Json(name="total") val total: Int,
) : Parcelable