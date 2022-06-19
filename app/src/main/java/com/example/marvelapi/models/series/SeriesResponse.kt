package com.example.marvelapi.models.series

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesResponse(
    @field:Json(name="data") val data: SeriesData
) : Parcelable