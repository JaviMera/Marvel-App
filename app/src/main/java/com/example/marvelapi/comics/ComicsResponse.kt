package com.example.marvelapi.comics

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsResponse(
    @field:Json(name="data") val data: ComicsData
) : Parcelable