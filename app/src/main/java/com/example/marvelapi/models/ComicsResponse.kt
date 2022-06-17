package com.example.marvelapi.models

import android.os.Parcelable
import com.example.marvelapi.models.ComicsData
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsResponse(
    @field:Json(name="data") val data: ComicsData
) : Parcelable