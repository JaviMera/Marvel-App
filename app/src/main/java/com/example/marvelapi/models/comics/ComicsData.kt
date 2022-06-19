package com.example.marvelapi.models.comics

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsData(
    @field:Json(name="results") val results: List<Comic>,
    @field:Json(name="total") val total: Int,
) : Parcelable