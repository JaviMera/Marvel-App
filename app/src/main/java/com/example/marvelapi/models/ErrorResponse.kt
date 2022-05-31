package com.example.marvelapi.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ErrorResponse(
    @field:Json(name="message") val message: String,
    @field:Json(name="code") val code: String
) : Parcelable